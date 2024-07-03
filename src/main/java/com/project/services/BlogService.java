package com.project.services;

import com.project.dao.IBlogDAO;
import com.project.dao.implement.FactoryDAO;
import com.project.models.Blog;
import com.project.models.Image;
import com.project.models.User;
import jakarta.servlet.http.HttpServletRequest;
import org.jdbi.v3.core.Handle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class BlogService extends AbstractService {
    private final IBlogDAO blogDAO;

    public BlogService() {
        super();
        this.blogDAO = FactoryDAO.getDAO(handle, FactoryDAO.DAO_BLOG);
    }

    public BlogService(Handle handle) {
        super(handle);
        this.blogDAO = FactoryDAO.getDAO(handle, FactoryDAO.DAO_BLOG);
    }

    public int insertBlog(String hostName, String dest, User author, String title, boolean status, String description, String blogContent, Image thumbnail, LocalDateTime createAt, LocalDateTime updateAt) throws Exception {
//            Format nội dung này lại rồi viết thành file trong server, rồi lấy ra đường dẫn tương đối của file này
        File file = __formatBlogContent__(blogContent, hostName, dest);
        String path = file.getPath().substring(file.getPath().lastIndexOf("blogs")).replaceAll("\\\\", "/");
        System.out.println(path);
        ImageService imgService = new ImageService(this.handle);
//            tiến hành insert blog vào database
        Blog blog = new Blog(-1, author, title, status, description, path, thumbnail, updateAt, createAt);
        int blogId = blogDAO.insert(blog);
        blog.setId(blogId);
//            Sau khi insert xong thì tiến hành lấy ra toàn bộ ảnh trong blog này
        blogContent = readBlog(file);
        Document document = Jsoup.parse(blogContent);
        var imgs = document.getElementsByTag("img").stream().toList();
        for (var img : imgs) {
            var image = imgService.selectByPath(img.attr("src"));
//                insert các hình ảnh này vào bảng blog_galleries trong database
            imgService.insertToGalleryOf(blog, image);
        }
        return blogId;
    }

    public String readBlog(File file) throws IOException {
        return IOService.getInstance().readTxtFile(file);
    }
    public String readSavedBlog(String url, String context) throws IOException {
        String contentBlog = IOService.getInstance().readText(url);
        var document = Jsoup.parse(contentBlog);
        document.getElementsByTag("img").stream().forEach(img -> {
            String path = img.attr("src");
            img.attr("src", context+"/files/"+path);
        });
        return document.body().toString();
    }
      public String readSavedBlog(String url, HttpServletRequest request) throws IOException {
        String contentBlog = IOService.getInstance().readText(url);
        String context = request.getServletContext().getContextPath();
        var document = Jsoup.parse(contentBlog);
        document.getElementsByTag("img").stream().forEach(img -> {
            String path = img.attr("src");
            img.attr("src", context+"/files/"+path);
        });
        return document.body().toString();
    }

    public List<Blog> getAll() {
        return blogDAO.selectAll();
    }

    public Blog findById(int id) {
        return blogDAO.getBlogById(id);
    }

    private File __formatBlogContent__(String blogContent, String hostName, String dest) throws Exception {
        var document = Jsoup.parse(blogContent);
        var imgs = document.getElementsByTag("img").stream().toList();
        var iterator = imgs.iterator();
        ImageService imgService = new ImageService(this.handle);
        while (iterator.hasNext()) {
            var current = iterator.next();
            String src = current.attr("src");
            URL url = new URL(src);
//          Nếu host của ảnh là host của server:
            if (hostName.equals(url.getHost())) {
//            Cắt toàn bộ phần protocol://host/WebContext trong attribute src của tag img
                int offset = url.getPath().lastIndexOf("images");
                if (offset == -1) continue;
                String filePath = url.getPath().substring(offset);
//                System.out.println("checking filePath: "+filePath);
//            Nếu ảnh đã được insert vào database (người dùng sử dụng ảnh có sẵn trong server)
                if (imgService.selectByPath(filePath) != null) {
                    current.attr("src", filePath);
//                    Loại bỏ thẻ img này ra khỏi danh sách
                    iterator.remove();
                }
            }
        }
        var month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        var day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
//        Insert các ảnh chưa có trong database
        String relativeToFile = null;
        String link = null;
        String uuid = null;
        String extension = null;
        String fileName = null;
        String folderContains = null;
//        List chứa các key của ảnh đã đc insert vào database
        for (var img : imgs) {
            link = img.attr("src");
            int id = imgService.downloadAndInsert(link, dest);
            relativeToFile = imgService.selectById(id).getPath();
            img.attr("src", relativeToFile);
        }
////        Write nội dung bài blog xuống thư mục của server
        uuid = UUID.randomUUID().toString().replaceAll("-","");
        extension = ".txt";
        fileName = String.format("%s%s", uuid, extension);
        folderContains = String.format("%s/blogs/%s/%s", dest, month, day);
        return IOService.getInstance().writeBlog(document.body().html(), folderContains, fileName);
    }
    public List<Blog> getAllBlog(){
        return blogDAO.selectAll();
    }
    public Blog getBlogById(int id){
        return blogDAO.getBlogById(id);
    }

    public static void main(String[] args) throws IOException {
        var service = new BlogService();
        System.out.println(service.findById(239));
        File file = new File("C:\\Users\\ADMIN\\Desktop\\test\\blogs/12/26/5e1c5db2acab4f83803fe10f61977d8a.txt");
        var blogService = new BlogService();
        String blogContent = blogService.readBlog(file);
//        try {
//            blogService.begin();
//            blogService.insertBlog("bla", "C:\\Users\\ADMIN\\Desktop\\test", null, "bla", false, "mô tả", blogContent, null, null, null);
//            throw new Exception("rollback transaction này!");
//        } catch (Exception e) {
//            e.printStackTrace();
//            blogService.rollback();
//        } finally {
//            blogService.close();
//        }
        System.out.println(blogService.getAllBlog().toString());
//        System.out.println(blogContent);
    }
}

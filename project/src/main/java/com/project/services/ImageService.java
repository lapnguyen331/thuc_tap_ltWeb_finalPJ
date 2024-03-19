package com.project.services;

import com.project.dao.IImageDAO;
import com.project.dao.implement.FactoryDAO;
import com.project.models.Blog;
import com.project.models.Image;
import com.project.models.Product;
import jakarta.servlet.http.Part;
import org.jdbi.v3.core.Handle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class ImageService extends AbstractService{
    private final IImageDAO imageDAO;
    public ImageService() {
        super();
        this.imageDAO = FactoryDAO.getDAO(handle, FactoryDAO.DAO_IMAGE);
    }

    public ImageService(Handle handle) {
        super(handle);
        this.imageDAO = FactoryDAO.getDAO(handle, FactoryDAO.DAO_IMAGE);
    }

    public int insert(Image image) throws Exception {
        return imageDAO.insert(image);
    }

    public Image selectByPath(String path) {
        return imageDAO.selectByPath(path);
    }

    public void insertToGalleryOf(Blog blog, Image image) throws Exception {
        imageDAO.insertToGalleryOf(blog, image);
    }

    public void insertToGalleryOf(Product product, Image image) {
        imageDAO.insertToGalleryOf(product, image);
    }

    public Image selectById(int id) {
        return imageDAO.selectById(id);
    }

    public void downImageFromURL(String url, String folderContain, String fileName) throws IOException {
        IOService.getInstance().downloadImageFromURL(url, folderContain, fileName);
    }

    public int downloadAndInsert(String url, String dest) throws Exception {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = url.substring(url.lastIndexOf("."));
        String fileName = uuid+extension;
        var month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        var day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        String folderContains = String.format("%s/images/%s/%s", dest, month, day);
        String relativePath = String.format("images/%s/%s/%s", month, day, fileName);
        var image = new Image(-1, relativePath, uuid, null, null);

        downImageFromURL(url, folderContains, fileName);
        return imageDAO.insert(image);
    }

    public int createAndInsert(Part part, String dest) throws IOException {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));
        String fileName = uuid+extension;
        var month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        var day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        String folderContains = String.format("%s/images/%s/%s", dest, month, day);
        Files.createDirectories(Path.of(folderContains));
        part.write(folderContains+"/"+fileName);
        String relativePath = String.format("images/%s/%s/%s", month, day, fileName);
        var image = new Image(-1, relativePath, uuid, null, null);
        return imageDAO.insert(image);
    }

    public int clearGalleries(Product product) {
        return imageDAO.removeGalleryOf(product);
    }

    public List<Image> getGalleriesOf(Product product) {
        return imageDAO.selectGalleryOf(product);
    }

    public Image findOneById(int id) {
        return imageDAO.selectById(id);
    }
    public Image findOneByUUID(String uuid) {
        return imageDAO.selectByUUID(uuid);
    }

    List<Image> findAll() {
        return imageDAO.selectAll();
    }

    public static void main(String[] args) {
//       ImageService em =  new ImageService();
//       Product p = new Product();
//       p.setId(1);
//       em.getGalleriesOf(p).forEach(System.out::println);
    }

}

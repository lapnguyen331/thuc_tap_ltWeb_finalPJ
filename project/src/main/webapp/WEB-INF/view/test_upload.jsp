<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Test Upload Với Cloudinary</title>
    <link
            href="https://unpkg.com/filepond-plugin-image-preview/dist/filepond-plugin-image-preview.css"
            rel="stylesheet"
    />
    <!-- add before </body> -->
    <script src="https://unpkg.com/filepond-plugin-image-preview/dist/filepond-plugin-image-preview.js"></script>
    <link href="https://unpkg.com/filepond/dist/filepond.css" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/libs/mdb-bootstrap-5-pro/css/mdb.min.css">
    <style>
        .filepond--drop-label {
            color: #4c4e53;
        }

        .filepond--label-action {
            text-decoration-color: #babdc0;
        }

        .filepond--panel-root {
            border-radius: 2em;
            background-color: #edf0f4;
            height: 1em;
        }

        .filepond--item-panel {
            background-color: #595e68;
        }

        .filepond--drip-blob {
            background-color: #7f8a9a;
        }
        .filepond--item {
            width: calc(20% - 0.5em);
        }
    </style>
</head>
<body>
    <div class="d-flex justify-content-center" id="loading-bar">
        <img src="${pageContext.request.contextPath}/inventory/images/loading-gif.gif" width="100px" alt="">
    </div>
    <input type="file"
           class="filepond"
           name="filepond"
           multiple
           data-max-file-size="3MB"
           data-max-files="8" style="display: none">
    <script
            src="https://code.jquery.com/jquery-3.7.1.js"
            integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
            crossorigin="anonymous"
    ></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="https://unpkg.com/filepond/dist/filepond.js"></script>
    <script>
        (async function() {
            let {data: {keys}} = await axios.get(`${pageContext.request.contextPath}/cloudinary/search`);
            keys = keys.map(value => {
                return {
                    // the server file reference
                    source: value,
                    // set type to limbo to tell FilePond this is a temp file
                    options: {
                        type: 'limbo',
                    },
                }
            })
            $('#loading-bar').addClass('d-none')
            FilePond.registerPlugin(FilePondPluginImagePreview);
            // Turn input element into a pond with configuration options
            FilePond.create($('.filepond').get()[0], {
                allowProcess: true,
                allowPaste: true,
                allowMultiple: true,
                allowRevert: true,
                forceRevert: true,
                instantUpload: false,
            });
            FilePond.setOptions({
                files: keys,
                server: {
                    process: (fieldName, file, metadata, load, error, progress, abort, transfer, options) => {
                        const API_KEY = '367879416323992'
                        const formData = new FormData();
                        const api = `https://api.cloudinary.com/v1_1/dqki124o5/auto/upload`;
                        const CancelToken = axios.CancelToken;
                        const source = CancelToken.source();
                        const doUpload = async function() {
                            const {data: signatureData} = await axios.get(`${pageContext.request.contextPath}/cloudinary/get-signature`);
                            formData.append('timestamp', signatureData.timestamp);
                            formData.append('signature', signatureData.signature);
                            formData.append('folder', signatureData.folder);
                            formData.append('api_key', API_KEY);
                            formData.append("file", file);
                            const {data: res} = await axios.post(api, formData, {
                                headers: {
                                    "Content-Type": "multipart/form-data",
                                },
                                onUploadProgress: ({loaded, total, bytes, estimated, rate, event}) => {
                                    console.log(loaded + '/' + total);
                                    progress(event.lengthComputable, loaded, total)
                                },
                                cancelToken: source.token,
                            }).catch(function (throws) {
                                if (axios.isCancel(throws)) console.log('request bị cancel do', throws.message)
                                abort();
                                return {
                                    data: 'failed'
                                }
                            });
                            const folder = res.folder;
                            let name = res.public_id.replace(folder+'/', '')
                            console.log(name)
                            load(name);
                        }
                        doUpload();
                        return {
                            abort: () => {
                                abort();
                                // This function is entered if the user has tapped the cancel button
                                source.cancel('Test hủy bỏ');
                            },
                        }
                    },
                    restore: (uniqueFileId, load, error, progress, abort, headers) => {
                        const doRestore = async function() {
                            axios.get(`${pageContext.request.contextPath}/cloudinary/restore-image`, {
                                params: {id: uniqueFileId},
                                responseType: 'blob',
                            }).then(({data}) => {
                                load(data)
                            });
                        }
                        doRestore();
                    },
                    revert: function (id, load, error) {
                        //setTimeout(() => error('oh my god'), 10)
                        const doRevert = async function() {
                            axios.get(`${pageContext.request.contextPath}/cloudinary/revert-upload`, {
                                params: {id: id}
                            }).then(response => {
                                console.log(response)
                                load();
                            }).catch(thrown => {
                                thrown && setTimeout(() => error('Có lỗi xảy ra...'), 10)
                            })
                        }
                        doRevert();
                    },
                    load: (source, load, error, progress, abort, headers) => {
                        const doLoad = async function() {
                            axios.get(`${pageContext.request.contextPath}/cloudinary/get-image`, {
                                params: {id: source},
                                responseType: 'blob',
                            }).then(({data}) => {
                                load(data)
                            });
                        }
                        doLoad();
                        return {
                            abort: () => {
                                //abort();
                            },
                        }
                    },
                    remove: (source, load, error) => {
                        // Should somehow send `source` to server so server can remove the file with this source
                        console.log('remove', source)
                        // Can call the error method if something is wrong, should exit after
                        //error('oh my goodness');

                        // Should call the load method when done, no parameters required;
                        const doDelete = async function() {
                            axios.get(`${pageContext.request.contextPath}/cloudinary/remove-image`, {
                                params: {id: source},
                            }).then((response) => {
                                console.log(response)
                                load('')
                            });
                        }
                        doDelete();
                    },
                },
            });
        })();
    </script>
</body>
</html>

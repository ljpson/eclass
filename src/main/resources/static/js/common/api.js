let Api = {

    get: function (url, param, successFunc, errorFunc) {
        $.ajax({
            type: "GET",
            url: Constant.CONTEXT_PATH + url,
            data: param,
            success: function (res) {
                if (undefined !== successFunc && "function" === typeof successFunc) {
                    successFunc(res);
                }
            },
            error: function (e) {
                if ("function" === typeof errorFunc) {
                    errorFunc(e);
                }
            }
        })
    },

    post: function (url, param, successFunc, errorFunc) {
        $.ajax({
            type: "POST",
            url: Constant.CONTEXT_PATH + url,
            data: param,
            success: function (res) {
                if (undefined !== successFunc && "function" === typeof successFunc) {
                    successFunc(res);
                }
            },
            error: function (e) {
                if ("function" === typeof errorFunc) {
                    errorFunc(e);
                }
            }
        })
    },

    put: function (url, param, successFunc, errorFunc) {
        $.ajax({
            type: "PUT",
            url: Constant.CONTEXT_PATH + url,
            data: JSON.stringify(param),
            contentType: "application/json",
            success: function (res) {
                if (undefined !== successFunc && "function" === typeof successFunc) {
                    successFunc(res);
                }
            },
            error: function (e) {
                if ("function" === typeof errorFunc) {
                    errorFunc(e);
                }
            }
        })
    },

    delete: function (url, param, successFunc, errorFunc) {
        $.ajax({
            type: "DELETE",
            url: Constant.CONTEXT_PATH + url,
            data: JSON.stringify(param),
            contentType: "application/json",
            success: function (res) {
                if (undefined !== successFunc && "function" === typeof successFunc) {
                    successFunc(res);
                }
            },
            error: function (e) {
                if ("function" === typeof errorFunc) {
                    errorFunc(e);
                }
            }
        })
    },

    multipart: function (url, param, successFunc, errorFunc) {
        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: Constant.CONTEXT_PATH + url,
            data: param,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                if (undefined !== successFunc && "function" === typeof successFunc) {
                    successFunc(data);
                }
            },
            error: function (e) {
                if ("function" === typeof errorFunc) {
                    errorFunc(e);
                }
            }
        })
    },
    /**
     * 학급 저장
     * @param param
     * @param callback
     */
    saveKlass: function (param, callback) {
        Api.put("/api/klass/save/"+param.teacherId, param, callback, callback);
    },
    /**
     * 학급 목록
     * @param param
     * @param callback
     */
    getKlassList: function (param, callback) {
        Api.get("/api/klass/"+param.teacherId, param, callback);
    },

    /**
     * 학급 저장
     * @param param
     * @param callback
     */
    deleteKlass: function (param, callback) {

        Api.put("/api/klass/delete", param, callback, callback);
    },
}
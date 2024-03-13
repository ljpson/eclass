let JsonUtil = {
    /**
     * formData json Object 로 변경
     * @param formData
     * @returns {{}}
     */
    formDataToJsonObject: function (formData) {
        let obj = {};
        formData.forEach(function (v) {
            obj[v["name"]] = v["value"];
        });
        return obj;
    },

    jsonToString: function (jsonData) {
        return JSON.stringify(jsonData)
    }
}
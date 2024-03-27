$(function () {
    getKlassList();
});

/**
 * 학급 저장 API호출
 */
function saveKlass() {
    let jsonData = JsonUtil.formDataToJsonObject($("#saveForm").serializeArray());
    Api.saveKlass(jsonData, function (res) {
        alert('학급정보 저장이 완료되었습니다.');
        getKlassList();
    });
}

/**
 * 학급 목록 API호출
 */
function getKlassList() {
    let jsonData = JsonUtil.formDataToJsonObject($("#searchForm").serializeArray());
    Api.getKlassList(jsonData, settingList);
}

/**
 * 학급 수정 API호출
 */
function updateKlass(row) {
    let jsonData = JsonUtil.formDataToJsonObject($("#saveForm_"+row+" input").serializeArray());
    Api.saveKlass(jsonData, function (res) {
        alert('학급정보 수정이 완료되었습니다.');
        getKlassList();
    });
}

/**
 * 학급 삭제 API호출
 */
function deleteKlass(row) {
    let jsonData = JsonUtil.formDataToJsonObject($("#saveForm_"+row+" input").serializeArray());
    Api.deleteKlass(jsonData, function (res) {
        alert('학급정보 삭제가 완료되었습니다.');
        getKlassList();
    });
}



/**
 * 학급 목록 table setting
 * @param res
 */
function settingList(res) {

    $("#listTable tbody").empty();
    const today = new Date();
    let tableWarp = [];
    let i = 1;
    console.log(res);
    if (res["status_code"] === 200) {
        if (res.data.length > 0) {
            $("#listTable").parent("div").find(".no_data").hide();
            for (let data of res.data) {
                tableWarp.push(`<tr id="saveForm_`+ i +`">
                                    <td>
                                        <input type="hidden" name="teacherId" value="` + data.teacherId + `">
                                        <input type="hidden" name="gradeType" value="` + data.gradeType + `">
                                        <input type="hidden" name="password" value="` + data.password + `">
                                        <input type="hidden" name="deleteYn" value="` + data.deleteYn + `">
                                        <input type="hidden" name="createBy" value="` + data.createBy + `">
                                        <input type="hidden" name="createDate" value="` + data.createDate + `">
                                        <input type="hidden" name="updateBy" value="` + data.teacherId + `">
                                        <input type="hidden" name="updateDate" value="` + common.yyyy_mm_dd(today) + `">
                                        <input type="hidden" name="id" value="` + data.klassId + `">
                                        ` + i + `</td>
                                    <td>` + data.neId + `</td>
                                    <td>` + data.teacherName + `</td>
                                    <td><input type='text' name='klassName' value='` + data.klassName + `'></td>
                                    <td><input type='text' name='studentAmount' value='` + data.studentAmount + `'></td>
                                    <td><button onclick="updateKlass(` + i + `)">수정</button></td>
                                    <td><button onclick="deleteKlass(` + i + `)">삭제</button></td>
                                </tr>`);

                i++;
            }
            $("#listTable tbody").append(tableWarp);
        } else {

            $("#listTable").parent("div").find(".no_data").show();
        }
    } else {
        alert(res["status_message"]);
    }

}


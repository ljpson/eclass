let common = {

    /**
     * 날짜 포맷 setting
     */
    yyyy_mm_dd: function (unixTime) {
        let date = new Date(unixTime);
        let yyyy = date.getFullYear();
        let mm = (date.getMonth() + 1 <= 9) ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        let dd = (date.getDate() <= 9) ? "0" + (date.getDate()) : date.getDate();

        return yyyy + "-" + mm + "-" + dd;
    },
}
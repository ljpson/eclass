let StringUtil = {
    /**
     * 빈값 체크
     * @param string
     * @returns {boolean}
     */
    isEmpty: function (string) {
        return string == null || string === '';
    },

    /**
     * 빈값이 아닌지 체크
     * @param string
     * @returns {boolean}
     */
    isNotEmpty: function (string) {
        return !this.isEmpty(string);
    },

    /**
     * 문자열 배열이 모두 빈값일 경우
     * @param strArr
     * @returns {*}
     */
    isAllEmpty: function (strArr) {
        let result = strArr.every(function (str) {
            return !!StringUtil.isEmpty(str);
        })
        return result;
    },

    /**
     * 문자열 배열에 빈값이 없을 경우
     * @param strArr
     * @returns {boolean}
     */
    isAllNotEmpty: function (strArr) {
        let result = strArr.every(function (str) {
            return !!StringUtil.isNotEmpty(str);
        })
        return result;
    },

    /**
     * 두개의 문자열이 같을 때
     * @param str1
     * @param str2
     * @returns {boolean}
     */
    isEqual: function (str1, str2) {
        return str1 === str2;
    },

    /**
     * 두개의 문자열이 같지 않을때
     * @param str1
     * @param str2
     * @returns {boolean}
     */
    isNotEqual: function (str1, str2) {
        return !StringUtil.isEqual(str1, str2);
    },

    /**
     * 빈 값일때 값 치환
     * @param value
     * @returns {string|*}
     */
    checkValue: function (value) {
        if (value == null || value.length === 0) {
            return '';
        } else {
            return value;
        }
    },

    /**
     * 이메일 유효성 검사
     * @param email
     * @returns {boolean}
     */
    validEmailCheck: function (email) {
        let pattern = new RegExp(/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/);
        return pattern.test(email);
    },

    /**
     * 핸드폰번호 유효성 검사
     * @param mobile
     * @returns {boolean}
     */
    validPhoneCheck: function (mobile) {
        let mobile_pattern = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}/
        return mobile_pattern.test(mobile);
    },

    /**
     *  날짜 포맷 변환
     * @param date
     * @returns {string}
     */
    formatDate: function (date) {
        const day = new Date(+new Date() + 3240 * 10000).toISOString().split("T")[0]
        const time = new Date().toTimeString().split(" ")[0];

        return day + ' ' + time;

    },

    /**
     * 파일 확장자 리턴
     *
     * @param fileName 파일명
     * @return {string}
     */
    getFileExtension: function (fileName) {
        return fileName.split('.').pop();
    },

    /**
     * 숫자 유효성 검사
     * @param mobile
     * @returns {boolean}
     */
    validNumberCheck: function (number) {
        let number_pattern = /^[0-9]+$/;
        return number_pattern.test(number);
    }

}
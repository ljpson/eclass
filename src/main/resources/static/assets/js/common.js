// OverlayScrollbars
const scrollBar = document.querySelectorAll(".s-scroll");
function scrollBarInit() {
  scrollBar.forEach((e) => {
    const osInstance = OverlayScrollbars(e, {});
  });
}
scrollBarInit();

// input text
document.querySelectorAll(".input-text input").forEach((e) => {
  // focus
  e.addEventListener("focus", function () {
    this.closest(".input-text").classList.add("focus");
  });

  // blur
  e.addEventListener("blur", function () {
    this.closest(".input-text").classList.remove("focus");
  });
});

// textarea
document.querySelectorAll(".input-text textarea").forEach((e) => {
  // focus
  e.addEventListener("focus", function () {
    this.closest(".input-text").classList.add("focus");
  });

  // blur
  e.addEventListener("blur", function () {
    this.closest(".input-text").classList.remove("focus");
  });
});

// contenteditable
document.querySelectorAll(".contenteditable").forEach((e) => {
  // focus
  e.addEventListener("focus", function () {
    if (e.readOnly) {
      return false;
    }
    e.closest(".contenteditable-wrap").classList.add("focus");
  });

  // blur
  e.addEventListener("blur", function () {
    e.closest(".contenteditable-wrap").classList.remove("focus");
  });
});

// selecbox
document.querySelectorAll(".selectBox .option").forEach((e) => {
  e.addEventListener("click", function () {
    document.querySelectorAll(".selectBox").forEach((e) => e.classList.remove("open-top"));

    if (this.closest(".selectBox").classList.contains("active")) {
      this.closest(".selectBox").classList.remove("active");
    } else {
      document.querySelectorAll(".selectBox").forEach((e) => e.classList.remove("active"));
      this.closest(".selectBox").classList.add("active");
    }

    // 스크롤 영역 밖으로 나가면 위로 열리게
    const dropdownList = this.closest(".selectBox").querySelector(".dropdown-list");
    const parentTop = this.closest(".s-scroll").getBoundingClientRect().top;
    const targetTop = dropdownList.getBoundingClientRect().top;
    const ellTop = targetTop - parentTop;

    // 위로 열릴때 selectBox에 open-top 추가
    if (ellTop + dropdownList.offsetHeight + 2 > this.closest(".s-scroll").offsetHeight) {
      this.closest(".selectBox").classList.add("open-top");
    } else {
      this.closest(".selectBox").classList.remove("open-top");
    }
  });
});
// selectbox option 선택
document.querySelectorAll(".selectBox .dropdown-list li").forEach((e) => {
  e.addEventListener("click", function () {
    const _value = this.textContent;
    const _parent = this.closest(".selectBox");

    _parent.querySelectorAll(".dropdown-list li").forEach((e) => e.classList.remove("selected"));
    this.classList.add("selected");

    _parent.querySelector(".option span").textContent = _value;
    if (_parent.querySelector(".option").classList.contains("placeholder")) {
      _parent.querySelector(".option").classList.remove("placeholder");
    }

    _parent.classList.remove("active");
    this.closest(".selectBox").classList.remove("open-top");
  });
});

// btn-dropdown
document.querySelectorAll(".btn-dropdown > button").forEach((e) => {
  e.addEventListener("click", function () {
    if (this.closest(".btn-dropdown").classList.contains("active")) {
      this.closest(".btn-dropdown").classList.remove("active");
    } else {
      document.querySelectorAll(".btn-dropdown").forEach((e) => e.classList.remove("active"));
      this.closest(".btn-dropdown").classList.add("active");
    }
  });
});
// btn-dropdown option
document.querySelectorAll(".btn-dropdown .dropdown-list li").forEach((e) => {
  e.addEventListener("click", function () {
    const _parent = this.closest(".btn-dropdown");

    if (this.closest(".dropdown-list").classList.contains("click-no-close")) return false;

    _parent.classList.remove("active");
  });
});

// tooltip-guide
document.querySelectorAll(".tooltip-guide").forEach((e) => {
  e.querySelectorAll(".tooltip-btn-wrap button").forEach((e) => {
    e.addEventListener("click", function () {
      this.closest(".tooltip-guide").style.display = "none";
    });
  });
});

// input show pwd
document.querySelectorAll(".input-text .btn-show-pwd").forEach((e) => {
  e.addEventListener("click", function () {
    _input = this.closest(".input-text").querySelector("input");
    this.classList.toggle("on");
    if (this.classList.contains("on")) {
      _input.type = "text";
    } else {
      _input.type = "password";
    }
  });
});

// s-scroll shadow check
function checkScrollHeight() {
  document.querySelectorAll(".s-scroll.scroll-shadow").forEach((e) => {
    const _this = e;
    const _child = e.querySelector(".scroll-shadow-inner");

    if (_this.offsetHeight < _child.offsetHeight) {
      _this.classList.add("shadow-on");
    } else {
      _this.classList.remove("shadow-on");
    }
  });
}
checkScrollHeight();

// s-scroll scrolled shadow
document.querySelectorAll(".s-scroll.scroll-shadow").forEach((e) => {
  e.querySelector(".os-viewport").addEventListener("scroll", function () {
    if (this.offsetHeight + this.scrollTop + 1 > this.scrollHeight) {
      this.closest(".s-scroll.scroll-shadow").classList.remove("shadow-on");
    } else {
      this.closest(".s-scroll.scroll-shadow").classList.add("shadow-on");
    }
  });
});

// window resize
window.addEventListener("resize", function () {
  checkScrollHeight();
});

// 밖 클릭시 닫힘
document.addEventListener("click", (e) => {
  // selectbox
  let selectBox = e.composedPath().includes(document.querySelector(".selectBox.active"));
  if (!selectBox) {
    document.querySelectorAll(".selectBox.active").forEach((e) => e.classList.remove("active"));
    document.querySelectorAll(".selectBox").forEach((e) => e.classList.remove("open-top"));
    resetScroll();
  }

  // btn-dropdown
  let btnDropdown = e.composedPath().includes(document.querySelector(".btn-dropdown.active"));
  if (!btnDropdown) {
    document.querySelectorAll(".btn-dropdown.active").forEach((e) => e.classList.remove("active"));
    resetScroll();
  }
});

// dropdown-list scroll reset
function resetScroll() {
  document.querySelectorAll(".dropdown-list").forEach((e) => {
    if (e.querySelector(".s-scroll .os-viewport")) {
      e.querySelector(".s-scroll .os-viewport").scrollTop = 0;
    }
  });
}

<template>
  <div class="container">
    <section
      class="
        section
        register
        min-vh-100
        d-flex
        flex-column
        align-items-center
        justify-content-center
        py-4
      "
    >
      <div class="container">
        <div class="row justify-content-center">
          <div
            class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center"
          >

            <div class="card mb-3">
              <div class="card-body">
                <div class="pt-4 pb-2">
                  <h5 class="card-title text-center pb-0 fs-4">Create an Account</h5>
                  <p class="text-center small">Enter your personal details to create account</p>
                </div>

                <form class="row g-3 needs-validation" novalidate>
                  <div class="col-12">
                    <label for="yourName" class="form-label">Your Name</label>
                    <input
                      type="name"
                      class="form-control"
                      id="yourName"
                      :class="{
                        'is-valid': isUserNameFocusAndValid,
                        'is-invalid': isUserNameFocusAndInvalid,
                      }"
                      v-model="userName"
                      @input="validateUserName"
                      @focus="isUserNameFocus = true"
                    />
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">올바른 이름을 입력해 주세요.</div>
                  </div>

                  <div class="col-12">
                    <label for="yourEmail" class="form-label">Your Email</label>
                    <input
                      type="email"
                      class="form-control"
                      id="yourEmail"
                      :class="{
                        'is-valid': isUserEmailFocusAndValid,
                        'is-invalid': isUserEmailFocusAndInValid,
                      }"
                      v-model="userEmail"
                      @input="validateEmail"
                      @focus="isUserEmailFocus = true"
                    />
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">올바른 Email 을 입력해 주세요.</div>
                  </div>

                  <div class="col-12">
                    <label for="yourPassword" class="form-label">Password</label>
                    <input
                      type="password"
                      class="form-control"
                      id="yourPassword"
                      :class="{
                        'is-valid': isUserPasswordFocusAndValid,
                        'is-invalid': isUserPasswordFocusAndInvalid,
                      }"
                      v-model="userPassword"
                      @input="validatePassword"
                      @focus="isUserPasswordFocus = true"
                    />
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">
                      1개 이상의 특수문자, 대소문자 및 숫자를 포함하고 8자리 이상이여야 합니다.
                    </div>
                  </div>
                  <div class="col-12">
                    <label for="yourPassword2" class="form-label">Password check</label>
                    <input
                      type="password"
                      class="form-control"
                      id="yourPassword2"
                      :class="{
                        'is-valid': isUserPassword2FocusAndValid,
                        'is-invalid': isUserPassword2FocusAndInvalid,
                      }"
                      v-model="userPassword2"
                      @input="validatePassword2"
                      @focus="isUserPassword2Focus = true"
                    />
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">비밀번호가 일치하지 않습니다.</div>
                  </div>

                  <div class="col-12">
                    <div class="form-check">
                      <input
                        class="form-check-input"
                        name="terms"
                        type="checkbox"
                        value=""
                        id="acceptTerms"
                        required
                      />
                      <label class="form-check-label" for="acceptTerms"
                        >I agree and accept the <a href="#">terms and conditions</a></label
                      >
                      <div class="invalid-feedback">You must agree before submitting.</div>
                    </div>
                  </div>

                  <div class="col-12">
                    <button @click="register" class="btn btn-success w-100" type="button">
                      Create Account
                    </button>
                  </div>
                  <div class="col-12">
                    <p class="small mb-0">
                      Already have an account? <router-link to="/login"> Log in</router-link>
                    </p>
                  </div>
                </form>
              </div>
            </div>

          
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import Vue from "vue";
import VueAlertify from "vue-alertify";

Vue.use(VueAlertify);

import http from "@/common/axios.js";

export default {
  name: "register",

  data() {
    return {
      // v-model
      userName: "",
      userEmail: "",
      userPassword: "",
      userPassword2: "",

      // focus
      isUserNameFocus: false,
      isUserEmailFocus: false,
      isUserPasswordFocus: false,
      isUserPassword2Focus: false,

      // validation
      isUserNameValid: false,
      isUserEmailValid: false,
      isUserPasswordValid: false,
      isUserPassword2Valid: false,

      // 회원 구분
      // groupCode: '001',
      // codeList: [],
      // userClsf: '001' // 일반회원 default
    };
  },
  computed: {
    isUserNameFocusAndValid() {
      return this.isUserNameFocus && this.isUserNameValid;
    },
    isUserNameFocusAndInvalid() {
      return this.isUserNameFocus && !this.isUserNameValid;
    },
    isUserEmailFocusAndValid() {
      return this.isUserEmailFocus && this.isUserEmailValid;
    },
    isUserEmailFocusAndInValid() {
      return this.isUserEmailFocus && !this.isUserEmailValid;
    },
    isUserPasswordFocusAndValid() {
      return this.isUserPasswordFocus && this.isUserPasswordValid;
    },
    isUserPasswordFocusAndInvalid() {
      return this.isUserPasswordFocus && !this.isUserPasswordValid;
    },
    isUserPassword2FocusAndValid() {
      return this.isUserPassword2Focus && this.isUserPassword2Valid;
    },
    isUserPassword2FocusAndInvalid() {
      return this.isUserPassword2Focus && !this.isUserPassword2Valid;
    },
  },
  methods: {
    validateUserName() {
      this.isUserNameValid = this.userName.length > 0 ? true : false;
      console.log(this.isUserNameValid);
    },
    validateEmail() {
      // ^ 시작일치, $ 끝 일치
      // {2, 3} 2개 ~ 3개
      // * 0회 이상, + 1회 이상
      // [-_.] - 또는 _ 또는 .
      // ? 없거나 1회
      let regexp =
        /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      this.isUserEmailValid = regexp.test(this.userEmail) ? true : false;
      console.log(this.isUserEmailValid);
    },
    validatePassword() {
      let patternEngAtListOne = new RegExp(/[a-zA-Z]+/); // + for at least one
      let patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/); // + for at least one
      let patternNumAtListOne = new RegExp(/[0-9]+/); // + for at least one

      this.isUserPasswordValid =
        patternEngAtListOne.test(this.userPassword) &&
        patternSpeAtListOne.test(this.userPassword) &&
        patternNumAtListOne.test(this.userPassword) &&
        this.userPassword.length >= 8
          ? true
          : false;
    },
    validatePassword2() {
      this.isUserPassword2Valid = this.userPassword == this.userPassword2 ? true : false;
    },
    register() {
      if (!this.isUserEmailValid || !this.isUserPasswordValid || !this.isUserPassword2Valid) return;

      http
        .post("/register", {
          userName: this.userName,
          userEmail: this.userEmail,
          userPassword: this.userPassword,
          // userClsf: this.userClsf,
        })

        .then(({ data }) => {
          console.log("RegisterVue: data : ");
          console.log(data);

          let $this = this;
          this.$alertify.alert("회원가입을 축하합니다. 로그인 페이지로 이동합니다", function () {
            $this.$router.push("/login");
          });
        })
        .catch((error) => {
          console.log("RegisterVue: error : ");
          console.log(error);
          if (error.response.status == "404") {
            this.$alertify.error("Opps!! 서버에 문제가 발생했습니다.");
          }
        });
    },
  },
  
};
</script>

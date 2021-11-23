<template>
  <main id="main" class="main">
    <div class="pagetitle">
      <h1>Mypage</h1>
      <hr />
    </div>
    <!-- End Page Title -->

    <section class="section profile">
      <div class="row">
        <div class="col-xl-4">
          <div class="card">
            <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">
              <img
                v-bind:src="userProfileImageUrl"
                alt="Profile"
                class="rounded-circle"
                width="150"
              />
              <h2 v-once>{{ userName }}</h2>
              <h4>POSITION :</h4>
              <div class="social-links mt-2">
                <a href="#" class="twitter"><i class="bi bi-twitter"></i></a>
                <a href="#" class="facebook"><i class="bi bi-facebook"></i></a>
                <a href="#" class="instagram"><i class="bi bi-instagram"></i></a>
                <a href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>
              </div>
            </div>
          </div>
        </div>

        <div class="col-xl-8">
          <div class="card">
            <div class="card-body pt-3">
              <!-- Bordered Tabs -->
              <ul class="nav nav-tabs nav-tabs-bordered">
                <li class="nav-item">
                  <button
                    class="nav-link active"
                    data-bs-toggle="tab"
                    data-bs-target="#profile-overview"
                  >
                    Overview
                  </button>
                </li>

                <li class="nav-item">
                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-edit">
                    Edit Profile
                  </button>
                </li>

                <li class="nav-item">
                  <button
                    class="nav-link"
                    data-bs-toggle="tab"
                    data-bs-target="#profile-change-password"
                  >
                    Change Password
                  </button>
                </li>
              </ul>
              <div class="tab-content pt-2">
                <div class="tab-pane fade show active profile-overview" id="profile-overview">
                  <h5 class="card-title mt-3">Profile Details</h5>
                  <hr />

                  <div class="row mt-2">
                    <div class="col-lg-3 col-md-4 label">ID</div>
                    <div class="col-lg-9 col-md-8">{{ userId }}</div>
                  </div>

                  <div class="row mt-2">
                    <div class="col-lg-3 col-md-4 label">Name</div>
                    <div class="col-lg-9 col-md-8">{{ userName }}</div>
                  </div>

                  <div class="row mt-2">
                    <div class="col-lg-3 col-md-4 label">Phone</div>
                    <div class="col-lg-9 col-md-8">{{ userPhone }}</div>
                  </div>

                  <div class="row mt-2">
                    <div class="col-lg-3 col-md-4 label">Email</div>
                    <div class="col-lg-9 col-md-8">{{ userEmail }}</div>
                  </div>

                  <div class="text-center mt-3">
                    <button @click="btnUserDelete" class="btn btn-sm btn-outline-danger">
                      회원탈퇴
                    </button>
                  </div>
                </div>

                <div class="tab-pane fade profile-edit pt-3" id="profile-edit">
                  <!-- Profile Edit Form -->
                  <div class="row mb-3">
                    <label for="profileImage" class="col-md-4 col-lg-3 col-form-label"
                      >Profile Image</label
                    >
                    <div class="col-md-8 col-lg-9">
                      <img v-bind:src="userProfileImageUrl" alt="Profile" width="100" />
                      <div class="pt-2">
                        <input
                          class="form-control form-control-sm"
                          type="file"
                          id="userImageFileUpload"
                          multiple
                        />
                      </div>
                    </div>
                  </div>

                  <div class="row mb-3">
                    <label for="fullName" class="col-md-4 col-lg-3 col-form-label">Name</label>
                    <div class="col-md-8 col-lg-9">
                      <input
                        name="fullName"
                        type="text"
                        class="form-control"
                        id="fullName"
                        v-model="userName"
                      />
                    </div>
                  </div>

                  <div class="row mb-3">
                    <label for="Phone" class="col-md-4 col-lg-3 col-form-label">Phone</label>
                    <div class="col-md-8 col-lg-9">
                      <input
                        name="phone"
                        type="text"
                        class="form-control"
                        id="Phone"
                        v-model="userPhone"
                      />
                    </div>
                  </div>

                  <div class="row mb-3">
                    <label for="Email" class="col-md-4 col-lg-3 col-form-label">Email</label>
                    <div class="col-md-8 col-lg-9">
                      <input
                        name="email"
                        type="email"
                        class="form-control"
                        id="Email"
                        v-model="userEmail"
                      />
                    </div>
                  </div>

                  <div class="text-center">
                    <button @click="btnEditProfile" class="btn btn-success">Save Changes</button>
                  </div>
                  <!-- End Profile Edit Form -->
                </div>

                <div class="tab-pane fade pt-3" id="profile-change-password">
                  <!-- Change Password Form -->
                  <div class="row mb-3">
                    <label for="currentPassword" class="col-md-4 col-lg-3 col-form-label"
                      >Current Password</label
                    >
                    <div class="col-md-8 col-lg-9">
                      <input
                        name="password"
                        type="password"
                        class="form-control"
                        id="currentPassword"
                        :class="{
                          'is-valid': isUserCurrentPasswordFocusAndValid,
                          'is-invalid': isUserCurrentPasswordFocusAndInvalid,
                        }"
                        v-model="userCurrentPassword"
                        @input="validateCurrentPassword"
                        @focus="isUserCurrentPasswordFocus = true"
                      />
                      <div class="valid-feedback">Valid.</div>
                      <div class="invalid-feedback">현재 비밀번호를 확인하세요!</div>
                    </div>
                  </div>

                  <div class="row mb-3">
                    <label for="newPassword" class="col-md-4 col-lg-3 col-form-label"
                      >New Password</label
                    >
                    <div class="col-md-8 col-lg-9">
                      <input
                        name="newpassword"
                        type="password"
                        class="form-control"
                        id="newPassword"
                        :class="{
                          'is-valid': isUserPasswordFocusAndValid,
                          'is-invalid': isUserPasswordFocusAndInvalid,
                        }"
                        v-model="newUserPassword"
                        @input="validatePassword"
                        @focus="isUserPasswordFocus = true"
                      />
                      <div class="valid-feedback">Valid.</div>
                      <div class="invalid-feedback">
                        1개 이상의 특수문자, 대소문자 및 숫자를 포함하고 8자리 이상이여야 합니다.
                      </div>
                    </div>
                  </div>

                  <div class="row mb-3">
                    <label for="renewPassword" class="col-md-4 col-lg-3 col-form-label"
                      >Re-enter New Password</label
                    >
                    <div class="col-md-8 col-lg-9">
                      <input
                        name="renewpassword"
                        type="password"
                        class="form-control"
                        id="renewPassword"
                        :class="{
                          'is-valid': isUserPassword2FocusAndValid,
                          'is-invalid': isUserPassword2FocusAndInvalid,
                        }"
                        v-model="newUserPassword2"
                        @input="validatePassword2"
                        @focus="isUserPassword2Focus = true"
                      />
                      <div class="valid-feedback">Valid.</div>
                      <div class="invalid-feedback">비밀번호가 일치하지 않습니다.</div>
                    </div>
                  </div>

                  <div class="text-center">
                    <button @click="btnChangePassword" class="btn btn-success">
                      Change Password
                    </button>
                  </div>

                  <!-- End Change Password Form -->
                </div>
              </div>
              <!-- End Bordered Tabs -->
            </div>
          </div>
        </div>
      </div>
      <hr />
    </section>
  </main>
</template>

<script>
import Vue from "vue";
import VueAlertify from "vue-alertify";
Vue.use(VueAlertify);

import http from "@/common/axios.js";

export default {
  name: "Mypage",

  data() {
    return {
      userName: this.$store.state.user.userName,
      userId: this.$store.state.user.userId,
      userEmail: this.$store.state.user.userEmail,
      userCurrentPassword: "",

      userPhone: this.$store.state.user.userPhone,
      userProfileImageUrl: this.$store.state.user.userProfileImageUrl,

      newUserPassword: "",
      newUserPassword2: "",

      isUserPasswordFocus: false,
      isUserPassword2Focus: false,
      isUserPasswordValid: false,
      isUserPassword2Valid: false,

      isUserCurrentPasswordFocus: false,
      isUserCurrentPasswordValid: false,
    };
  },
  computed: {
    isUserCurrentPasswordFocusAndValid() {
      return this.isUserCurrentPasswordFocus && this.isUserCurrentPasswordValid;
    },
    isUserCurrentPasswordFocusAndInvalid() {
      return this.isUserCurrentPasswordFocus && !this.isUserCurrentPasswordValid;
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
    validateCurrentPassword() {
      console.log("test  :" + this.$store.state.login.userPassword);
      this.isUserCurrentPasswordValid =
        this.userCurrentPassword == this.$store.state.login.userPassword ? true : false;
    },
    validatePassword() {
      let patternEngAtListOne = new RegExp(/[a-zA-Z]+/); // + for at least one
      let patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/); // + for at least one
      let patternNumAtListOne = new RegExp(/[0-9]+/); // + for at least one

      this.isUserPasswordValid =
        patternEngAtListOne.test(this.newUserPassword) &&
        patternSpeAtListOne.test(this.newUserPassword) &&
        patternNumAtListOne.test(this.newUserPassword) &&
        this.newUserPassword.length >= 8
          ? true
          : false;
    },
    validatePassword2() {
      this.isUserPassword2Valid = this.newUserPassword == this.newUserPassword2 ? true : false;
    },

    // changeFile(fileEvent) {
    //   if (fileEvent.target.files && fileEvent.target.files.length > 0) {
    //     for (var i = 0; i < fileEvent.target.files.length; i++) {
    //       const file = fileEvent.target.files[i];
    //       this.fileList.push(URL.createObjectURL(file));
    //     }
    //   }
    // },
    btnEditProfile() {
      var formData = new FormData();
      formData.append("userId", this.userId);
      formData.append("userName", this.userName);
      formData.append("userPhone", this.userPhone);
      formData.append("userEmail", this.userEmail);

      var attachFiles = document.querySelector("#userImageFileUpload");
      var cnt = attachFiles.files.length;
      for (var i = 0; i < cnt; i++) {
        formData.append("file", attachFiles.files[i]);
      }

      http
        .post("/editProfile", formData, { headers: { "Content-Type": "multipart/form-data" } })

        .then(({ data }) => {
          console.log("editProfile: data: ");
          console.log(data);

          let $this = this;

          this.$alertify.alert("회원정보가 변경되었습니다. 다시 로그인해주세요.", function () {
            $this.logout();
            $this.$router.push("/");
          });
        })
        .catch((error) => {
          console.log("EditProfile: error: ");
          console.log(error);
          if (error.response.status == "404") {
            this.$alertify.error("Opps!! 서버에 문제가 발생했습니다.");
          }
        });
      // http
      //   .post("/imageFileUpload", formData, { headers: { "Content-Type": "multipart/form-data" } })
      //   .then(({ data }) => {
      //     if (data.result == "login") {
      //       this.$router.push("/login");
      //     } else {
      //       this.$alertify.success("회원정보가 변경되었습니다. 다시 로그인해주세요.");
      //     }
      //   })
      //   .catch((error) => {
      //     console.log("InsertModalVue: error ");
      //     console.log(error);
      //   });
    },

    // btnEditProfile() {
    //   http
    //     .put("/editProfile", {
    //       userId: this.userId,
    //       userName: this.userName,
    //       userPhone: this.userPhone,
    //       userEmail: this.userEmail,
    //     })
    //     .then(({ data }) => {
    //       console.log("editProfile: data: ");
    //       console.log(data);

    //       let $this = this;

    //       this.$alertify.alert("회원정보가 변경되었습니다. 다시 로그인해주세요.", function () {
    //         $this.logout();
    //         $this.$router.push("/login");
    //       });
    //     })
    //     .catch((error) => {
    //       console.log("EditProfile: error: ");
    //       console.log(error);
    //       if (error.response.status == "404") {
    //         this.$alertify.error("Opps!! 서버에 문제가 발생했습니다.");
    //       }
    //     });
    // },

    btnChangePassword() {
      if (!this.isUserPasswordValid || !this.isUserPassword2Valid || !this.userCurrentPassword)
        return;

      http
        .put("/passwordUpdate", {
          userId: this.userId,
          userPassword: this.newUserPassword,
        })

        .then(({ data }) => {
          console.log("ChangePassword: data: ");
          console.log(data);

          let $this = this;

          this.$alertify.alert(
            "비밀번호가 변경되었습니다. 로그인 페이지로 이동합니다.",
            function () {
              $this.logout();
              $this.$router.push("/");
            }
          );
        })
        .catch((error) => {
          console.log("ChangePassword: error: ");
          console.log(error);
          if (error.response.status == "404") {
            this.$alertify.error("Opps!! 서버에 문제가 발생했습니다.");
          }
        });
    },
    btnUserDelete() {
      let $this = this;
      this.$alertify.confirm("정말로 탈퇴하시겠습니까?", function () {
        $this.$alertify.success("정상처리 되었습니다.");
        $this.userDelete();
      });
    },
    userDelete() {
      http
        .delete("/user/delete", {
          userId: this.userId,
        })
        .then(({ data }) => {
          console.log("userDelete : data: ");
          console.log(data);
          this.$store.commit("SET_LOGOUT");
          this.$router.push("/");
        })
        .catch((error) => {
          console.log("ChangePassword: error: ");
          console.log(error);
          if (error.response.status == "404") {
            this.$alertify.error("Opps!! 서버에 문제가 발생했습니다.");
          }
        });
    },
    logout() {
      http
        .get("/logout", {})
        .then(() => {
          this.$store.commit("SET_LOGOUT");
          console.log(this.$store.state.login.userName);
          //== <route-link :to=”path”>
          this.$router.push("/");
        })
        .catch((error) => {
          console.log("LoginVue: error : ");
          console.log(error);
          this.$alertify.error("Opps!! 서버에 문제가 발생했습니다.");
        });
    },
  },
};
</script>

<style></style>

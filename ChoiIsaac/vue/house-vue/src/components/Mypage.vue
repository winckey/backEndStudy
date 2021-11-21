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
              <h2>{{ userName }}</h2>
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
                </div>

                <div class="tab-pane fade profile-edit pt-3" id="profile-edit">
                  <!-- Profile Edit Form -->
                  <form>
                    <div class="row mb-3">
                      <label for="profileImage" class="col-md-4 col-lg-3 col-form-label"
                        >Profile Image</label
                      >
                      <div class="col-md-8 col-lg-9">
                        <img v-bind:src="userProfileImageUrl" alt="Profile" width="100" />
                        <div class="pt-2">
                          <a
                            href="#"
                            class="btn btn-primary btn-sm"
                            title="Upload new profile image"
                            ><i class="bi bi-upload"></i
                          ></a>
                          <a href="#" class="btn btn-danger btn-sm" title="Remove my profile image"
                            ><i class="bi bi-trash"></i
                          ></a>
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
                      <button type="submit" class="btn btn-primary">Save Changes</button>
                    </div>
                  </form>
                  <!-- End Profile Edit Form -->
                </div>

                <div class="tab-pane fade pt-3" id="profile-change-password">
                  <!-- Change Password Form -->
                  <form>
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
                        />
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
                        />
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
                        />
                      </div>
                    </div>

                    <div class="text-center">
                      <button type="submit" class="btn btn-primary">Change Password</button>
                    </div>
                  </form>
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

// import http from "@/common/axios.js";

export default {
  name: "Login",

  data() {
    return {
      userName: "sac",
      userId: "test",
      userEmail: "dskim@my.com",
      userPassword: "1234",

      userPhone: "010-2345-4333",
      userProfileImageUrl: "assets/img/author-1.jpg",
    };
  },
  methods: {},
};
</script>

<style></style>

<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
      <div class="navbar-collapse collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/patient/">Homepage</router-link>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/patient/clinics">Clinics</router-link>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/patient/examinations">Examinations</router-link>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/patient/operations">Operations</router-link>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/patient/editmedicalrecord">Medical record</router-link>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/patient/profile">Profile</router-link>
            </a>
          </li>
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              data-toggle="dropdown"
              href="#"
              role="button"
              aria-haspopup="true"
              aria-expanded="false"
            >Search</a>
            <div class="dropdown-menu">
              <a class="nav-link dropdown-item">
                <router-link to="/patient/searchdoctors">Doctors</router-link>
              </a>
              <a class="nav-link dropdown-item">
                <router-link to="/patient/searchclinics">Clinics</router-link>
              </a>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" v-on:click="logout">Logout</a>
          </li>
        </ul>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: "PHomepage",
  methods: {
    logout: function() {
      localStorage.removeItem("User-token");
      localStorage.removeItem("Expiary");
      localStorage.removeItem("Email");
      localStorage.removeItem("Authority");
      localStorage.removeItem("Refresh-token");
      this.$router.push("/login");
    }
  }
};
</script>

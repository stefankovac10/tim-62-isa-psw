<template>
  <div>
    <nav id="navBarCadmin" class="navbar navbar-expand-lg navbar-dark bg-primary">
      <div class="navbar-collapse collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/cadmin/clinic">Clinic</router-link>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/cadmin/businessReport">Business report</router-link>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/cadmin/addAppointment">Add appointment</router-link>
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
            >Manage rooms</a>
            <div class="dropdown-menu">
              <a class="nav-link dropdown-item">
                <router-link to="/cadmin/addRoom">Add room</router-link>
              </a>
              <a class="nav-link dropdown-item">
                <router-link to="/cadmin/rooms">Manage rooms</router-link>
              </a>
            </div>
          </li>
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              data-toggle="dropdown"
              href="#"
              role="button"
              aria-haspopup="true"
              aria-expanded="false"
            >Types of examinations</a>
            <div class="dropdown-menu">
              <a class="nav-link dropdown-item">
                <router-link to="/cadmin/addType">Add types of examination</router-link>
              </a>
              <a class="nav-link dropdown-item">
                <router-link to="/cadmin/types">Manage types of examination</router-link>
              </a>
            </div>
          </li>
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              data-toggle="dropdown"
              href="#"
              role="button"
              aria-haspopup="true"
              aria-expanded="false"
            >Manage medical staff</a>
            <div class="dropdown-menu">
              <a class="nav-link dropdown-item">
                <router-link to="/cadmin/addDoctor">Add medical staff</router-link>
              </a>
              <a class="nav-link dropdown-item">
                <router-link to="/cadmin/doctors">Medical staff</router-link>
              </a>
            </div>
          </li>
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              data-toggle="dropdown"
              href="#"
              role="button"
              aria-haspopup="true"
              aria-expanded="false"
              id="requestsNavButton"
            >Requests</a>
            <div class="dropdown-menu">
              <a class="nav-link dropdown-item">
                <router-link
                  id="examinationRoomRequests"
                  to="/cadmin/erRequests"
                >Examination room requests</router-link>
              </a>
              <a class="nav-link">
                <router-link to="/cadmin/operationRequests">Operation room requests</router-link>
              </a>
              <a class="nav-link">
                <router-link to="/cadmin/requests">Vacation requests</router-link>
              </a>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/cadmin/profile">Profile</router-link>
            </a>
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
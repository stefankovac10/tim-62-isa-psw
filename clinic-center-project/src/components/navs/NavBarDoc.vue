<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
      <div class="navbar-collapse collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/doc/patients">Patients</router-link>
            </a>
          </li>
          <!--
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/doc/examination">New examination</router-link>
            </a>
          </li>
          -->
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/doc/calendar">Calendar</router-link>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/doc/vacation">Vacation request</router-link>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/doc/profile">Profile</router-link>
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
  name: "NBDoc",
  methods: {
    logout: function() {
      localStorage.removeItem("User-token");
      localStorage.removeItem("Expiary");
      localStorage.removeItem("Email");
      localStorage.removeItem("Authority");
      this.$router.push("/login");
    }
  }
};
</script>

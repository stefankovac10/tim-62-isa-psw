<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
      <div class="navbar-collapse collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/ccadmin/requests">Requests</router-link>
            </a>
          </li>
          <li class="nav-item">
            <a
              class="nav-link dropdown-toggle"
              data-toggle="dropdown"
              href="#"
              role="button"
              aria-haspopup="true"
              aria-expanded="false"
            >Manage clinics</a>
            <div class="dropdown-menu">
              <a class="nav-link">
                <router-link to="/ccadmin/addClinic">Add clinic</router-link>
              </a>

              <a class="nav-link">
                <router-link to="/ccadmin/clinics">List of clinics</router-link>
              </a>
            </div>
          </li>
          <li class="nav-item">
            <a
              class="nav-link dropdown-toggle"
              data-toggle="dropdown"
              href="#"
              role="button"
              aria-haspopup="true"
              aria-expanded="false"
            >Manage admins</a>
            <div class="dropdown-menu">
              <a class="nav-link">
                <router-link to="/ccadmin/addCCAdmin">Add clinical centre administrator</router-link>
              </a>
              <a class="nav-link">
                <router-link to="/ccadmin/addCAdmin">Add clinic administrator</router-link>
              </a>
            </div>
          </li>
          <li class="nav-item">
            <a
              class="nav-link dropdown-toggle"
              data-toggle="dropdown"
              href="#"
              role="button"
              aria-haspopup="true"
              aria-expanded="false"
            >Manage medication</a>
            <div class="dropdown-menu">
              <a class="nav-link">
                <router-link to="/ccadmin/addMedicament">Add medication</router-link>
              </a>
              <a class="nav-link">
                <router-link to="/ccadmin/listMedicaments">List of medications</router-link>
              </a>
            </div>
          </li>
          <li class="nav-item">
            <a
              class="nav-link dropdown-toggle"
              data-toggle="dropdown"
              href="#"
              role="button"
              aria-haspopup="true"
              aria-expanded="false"
            >Manage diagnosis</a>
            <div class="dropdown-menu">
              <a class="nav-link">
                <router-link to="/ccadmin/addDiagnosis">Add diagnosis</router-link>
              </a>
              <a class="nav-link">
                <router-link to="/ccadmin/listDiagnosis">List of diagnosis</router-link>
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
  name: "NBCCAdmin",
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

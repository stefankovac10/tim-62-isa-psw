<template>
  <div class="d-flex p-2 justify-content-center">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
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
            >Clinic</a>
            <div class="dropdown-menu">
              <a class="nav-link">
                <router-link to="/ccadmin/addClinic">Add clinic</router-link>
              </a>

              <a class="nav-link">
                <router-link to="/ccadmin/clinics">Clinis</router-link>
              </a>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/ccadmin/admins">Admins</router-link>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/ccadmin/medicament">Medicament</router-link>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/ccadmin/diagnosis">Diagnosis</router-link>
            </a>
          </li>
        </ul>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: "NBCCAdmin"
};
</script>

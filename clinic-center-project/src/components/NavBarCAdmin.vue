<template>
  <div class="d-flex p-2 justify-content-center">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="navbar-collapse collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <a class="navbar link"
              ><router-link to="/cadmin/clinic">Clinic</router-link></a
            >
          </li>
          <li class="nav-item">
            <a class="navbar link"
              ><router-link to="/cadmin/businessReport"
                >Business report</router-link
              ></a
            >
          </li>
          <li class="nav-item">
            <a class="navbar link"
              ><router-link to="/cadmin/addAppointment"
                >Add appointment</router-link
              ></a
            >
          </li>
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              data-toggle="dropdown"
              href="#"
              role="button"
              aria-haspopup="true"
              aria-expanded="false"
              >Manage rooms</a
            >
            <div class="dropdown-menu">
              <a class="navbar link dropdown-item"
                ><router-link to="/cadmin/addRoom">Add room</router-link></a
              ><a class="navbar link dropdown-item"
                ><router-link to="/cadmin/rooms">Manage rooms</router-link></a
              >
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
              >Types of examinations</a
            >
            <div class="dropdown-menu">
              <a class="navbar link dropdown-item"
                ><router-link to="/cadmin/addType"
                  >Add types of examination</router-link
                ></a
              ><a class="navbar link dropdown-item"
                ><router-link to="/cadmin/types"
                  >Manage types of examination</router-link
                ></a
              >
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
              >Manage doctors</a
            >
            <div class="dropdown-menu">
              <a class="navbar link dropdown-item"
                ><router-link to="/cadmin/addDoctor">Add doctor</router-link></a
              ><a class="navbar link dropdown-item"
                ><router-link to="/cadmin/doctors">Doctors</router-link></a
              >
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/cadmin/profile">Profile </router-link>
            </a>
          </li>
        </ul>
      </div>
    </nav>
  </div>
</template>

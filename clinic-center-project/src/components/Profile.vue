<template>
  <div class="d-flex p-2 justify-content-center" v-bind:key="componentKey">
    <div class="d-flex flex-column p-2">
      <h1>Profile</h1>
      <button
        type="button"
        class="btn btn-primary"
        data-toggle="modal"
        data-target="#editModal"
        data-whatever="@mdo"
        v-on:click="editProfile"
      >Edit profile</button>

      <p>First name: {{ user.firstName }} {{ user.lastName }}</p>
      <p>JMBG: {{ user.jmbg }}</p>
      <p>Telephone: {{ user.telephone }}</p>
      <p>Address: {{ user.address }}, {{ user.city }}, {{ user.country }}</p>
      <p>Email: {{ user.email }}</p>
      <!-- <p>Assigned clinic: {{ user.clinic }}</p> -->
    </div>
    <div id="editModal" class="modal">
      <div class="modal-dialog justify-content-center" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Edit profile</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <form id="login" accept-charset="UTF-8" class="d-flex flex-column">
            <div class="modal-body d-flex flex-column">
              <label class="p-2">First name</label>
              <input type="text" class="p-2" id="name" name="firstName" v-model="firstName" />

              <label class="p-2">Last name</label>
              <input type="text" class="p-2" id="lastName" name="lastName" v-model="lastName" />

              <label class="p-2">JMBG</label>
              <input type="text" class="p-2" id="jmbg" name="jmbg" v-model="jmbg" />

              <label class="p-2">Telephone</label>
              <input type="text" class="p-2" id="telephone" name="telephone" v-model="telephone" />

              <label class="p-2">Country</label>
              <input type="text" class="p-2" id="country" name="country" v-model="country" />

              <label class="p-2">City</label>
              <input type="text" class="p-2" id="city" name="city" v-model="city" />

              <label class="p-2">Address</label>
              <input type="text" class="p-2" id="address" name="address" v-model="address" />
            </div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-primary"
                data-dismiss="modal"
                v-on:click.prevent="update"
              >Save changes</button>
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";

export default {
  data: function() {
    return {
      user: {},
      componentKey: 0,
      id: 0,
      firstName: undefined,
      lastName: undefined,
      jmbg: undefined,
      telephone: undefined,
      country: undefined,
      city: undefined,
      address: undefined
    };
  },
  mounted() {
    if (localStorage.getItem("Authority") === "ROLE_CADMIN") {
      this.id = 4;
    } else this.id = 13;

    httpClient
      .get("/users/profile/" + this.id)
      .then(response => {
        this.user = response.data;
      })
      .catch(error => {
        if (error.response.status == 302) this.user = error.response.data;
      });
  },
  methods: {
    editProfile: function() {
      // this.$router.push("/cadmin/editProfile/" + this.id);
      this.firstName = this.user.firstName;
      this.lastName = this.user.lastName;
      this.jmbg = this.user.jmbg;
      this.telephone = this.user.telephone;
      this.address = this.user.address;
      this.city = this.user.city;
      this.country = this.user.country;
    },
    update: function() {
      let newProfile = {
        id: this.user.id,
        firstName: this.firstName,
        lastName: this.lastName,
        jmbg: this.jmbg,
        telephone: this.telephone,
        country: this.country,
        city: this.city,
        address: this.address,
        email: this.email
      };

      httpClient
        .put("/users/edit", newProfile)
        .then(response => {
          response;
          this.user.firstName = this.firstName;
          this.user.lastName = this.lastName;
          this.user.jmbg = this.jmbg;
          this.user.telephone = this.telephone;
          this.user.country = this.country;
          this.user.city = this.city;
          this.user.address = this.address;
        })
        .catch(error => {
          alert(error);
        });

      this.componentKey += 1;
      this.$forceUpdate();
    }
  }
};
</script>

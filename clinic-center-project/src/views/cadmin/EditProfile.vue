<template>
  <div class="d-flex p-2 justify-content-center">
    <div class="d-flex p-2 justify-content-center col-sm-4">
      <form id="login" accept-charset="UTF-8" class="d-flex flex-column">
        <h1 class="p-2">Edit profile</h1>
        <br />

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
        <br />

        <label class="p-2" for="password">Password</label>
        <input
          type="password"
          class="p-2"
          id="password"
          name="password"
          v-bind="password"
          placeholder="Password"
        />

        <label class="p-2" for="confirmPassword">Confirm password</label>
        <input
          type="password"
          class="p-2"
          id="confirmPassword"
          name="confirmPassword"
          v-bind="confirmPassword"
          placeholder="Confirm password"
        />
        <label class="p-2" for="matching" id="matching">{{matching}}</label>

        <br />
        <button class="btn btn-primary p-2" v-on:click.prevent="update">Update</button>
      </form>
    </div>
  </div>
</template>


<script>
import { httpClient } from "@/services/Api.js";

export default {
  data: function() {
    return {
      firstName: undefined,
      lastName: undefined,
      jmbg: undefined,
      telephone: undefined,
      country: undefined,
      city: undefined,
      address: undefined,
      password: undefined,
      confirmPassword: undefined,
      matching: "",
      user: undefined
    };
  },
  watch: {
    confirmPassword() {
      if (this.confirmPassword != this.password)
        this.matching = "Passwords are not matching!";
      else this.matching = "";
    }
  },
  mounted() {
    httpClient
      .get("/users/profile/1")
      .then(response => {
        this.user = response.data;
        this.firstName = response.data.firstName;
        this.lastName = response.data.lastName;
        this.jmbg = response.data.jmbg;
        this.telephone = response.data.telephone;
        this.country = response.data.country;
        this.city = response.data.city;
        this.address = response.data.address;
      })
      .catch(error => {
        if (error.response.status == 302) {
          this.user = error.response.data;
          this.firstName = error.response.data.firstName;
          this.lastName = error.response.data.lastName;
          this.jmbg = error.response.data.jmbg;
          this.telephone = error.response.data.telephone;
          this.country = error.response.data.country;
          this.city = error.response.data.city;
          this.address = error.response.data.address;
        }
      });
  },
  methods: {
    update: function() {
      var newProfile = {
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
          alert(response.data);
        })
        .catch(error => {
          alert(error.response.data);
        });

      this.$router.push("/cadmin/profile");
    }
  }
};
</script>

<style scoped>
#matching {
  color: red;
}
</style>
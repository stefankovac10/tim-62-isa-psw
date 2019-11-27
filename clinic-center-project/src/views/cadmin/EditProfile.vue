<template>
  <div class="d-flex p-2 justify-content-center">
    <div class="d-flex p-2 justify-content-center col-sm-4">
      <form id="login" accept-charset="UTF-8" class="d-flex flex-column">
        <h1 class="p-2">Edit profile</h1>
        <br />

        <label class="p-2">Name</label>
        <input type="text" class="p-2" id="name" name="name" v-model="name" />

        <label class="p-2">Surname</label>
        <input type="text" class="p-2" id="surname" name="surname" v-model="surname" />

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

        <label for="staticEmail" class="p-2">E-mail</label>
        <input
          type="email"
          class="p-2"
          id="email"
          name="email"
          v-model="email"
          aria-describedby="emailHelp"
          placeholder="Enter email"
        />
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
      name: undefined,
      surname: undefined,
      jmbg: undefined,
      telephone: undefined,
      country: undefined,
      city: undefined,
      address: undefined,
      email: undefined,
      password: undefined,
      confirmPassword: undefined,
      matching: ""
    };
  },
  watch: {
    confirmPassword() {
      if (this.confirmPassword != this.password)
        this.matching = "Passwords are not matching!";
      else this.matching = "";
    }
  },
  methods: {
    update: function() {
      var newProfile = {
        firstName: this.name,
        lastName: this.surname,
        jmbg: this.jmbg,
        telephone: this.telephone,
        country: this.country,
        city: this.city,
        address: this.address,
        email: this.email
      };

      httpClient.put("/users/edit", newProfile);

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
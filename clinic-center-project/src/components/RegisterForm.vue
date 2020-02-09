<template>
  <div class="d-flex p-2 justify-content-center">
    <form id="login" accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
      <h1 class="p-2">Add new</h1>
      <br />

      <div class="d-flex justify-content-between">
        <label class="p-2">First name</label>
        <label for="text" class="p-2" id="err">{{firstNameMessage}}</label>
      </div>
      <input type="text" class="p-2" id="firstName" name="firstName" v-model="firstName" v-on:blur="firstNameBlank"/>

      <div class="d-flex justify-content-between">
        <label class="p-2">Last name</label>
        <label for="text" class="p-2" id="err">{{lastNameMessage}}</label>
      </div>
      <input type="text" class="p-2" id="lastName" name="lastName" v-model="lastName" v-on:blur="lastNameBlank"/>

      <div class="d-flex justify-content-between">
        <label class="p-2">JMBG</label>
        <label for="text" class="p-2" id="err">{{jmbgMessage}}</label>
      </div>
      <input type="text" class="p-2" id="jmbg" name="jmbg" v-model="jmbg" v-on:blur="jmbgBlank"/>

      <div class="d-flex justify-content-between">
        <label class="p-2">Telephone</label>
        <label for="text" class="p-2" id="err">{{telephoneMessage}}</label>
      </div>
      <input type="text" class="p-2" id="telephone" name="telephone" v-model="telephone" v-on:blur="telephoneBlank"/>

      <div class="d-flex justify-content-between">
        <label class="p-2">Country</label>
        <label for="text" class="p-2" id="err">{{countryMessage}}</label>
      </div>
      <input type="text" class="p-2" id="country" name="country" v-model="country" v-on:blur="countryBlank"/>

      <div class="d-flex justify-content-between">
        <label class="p-2">City</label>
        <label for="text" class="p-2" id="err">{{cityMessage}}</label>
      </div>
      <input type="text" class="p-2" id="city" name="city" v-model="city" v-on:blur="cityBlank"/>

      <div class="d-flex justify-content-between">
        <label class="p-2">Address</label>
        <label for="text" class="p-2" id="err">{{addressMessage}}</label>
      </div>
      <input type="text" class="p-2" id="address" name="address" v-model="address" v-on:blur="addressBlank"/>

      <div class="d-flex justify-content-between">
        <label for="staticEmail" class="p-2">E-mail</label>
        <label for="text" class="p-2" id="err">{{emailMessage}}</label>
      </div>
      <input
        type="email"
        class="p-2"
        id="email"
        name="email"
        v-model="email"
        aria-describedby="emailHelp"
        placeholder="Enter email"
        v-on:blur="emailBlank"
      />

      <div class="d-flex justify-content-between">
        <label class="p-2" for="password">Password</label>
        <label for="text" class="p-2" id="err">{{passwordMessage}}</label>
      </div>
      <input
        type="password"
        class="p-2"
        id="password"
        name="password"
        v-model="password"
        placeholder="Password"
        v-on:blur="passwordBlank"
      />

      <label class="p-2" for="confirmPassword">Confirm password</label>
      <input
        type="password"
        class="p-2"
        id="confirmPassword"
        name="confirmPassword"
        v-model="confirmPassword"
        placeholder="Confirm password"
      />
      <label class="p-2" for="matching" id="matching">{{matching}}</label>

      <div v-if="doc" class="d-flex flex-column">
        <div class="d-flex justify-content-between">
          <label class="p-2">Type</label>
          <label for="text" class="p-2" id="err">{{typeMessage}}</label>
        </div>
        <select class="p-2" id="type" name="type" v-model="type" v-on:blur="typeBlank">
          <option>Doctor</option>
          <option>Nurse</option>
        </select>
        <div class="d-flex flex-column" v-if="type==='Doctor'">
          <div class="d-flex justify-content-between">
            <label class="p-2" for="tyepOfExamination">Specialised type of examination</label>
            <label for="text" class="p-2" id="err">{{typeOfExMessage}}</label>
          </div>
          <select class="p-2" name="typeOfExamination" id="typeEx" v-model="typeEx" v-on:blur="typeExBlank">
            <option v-for="t in types" v-bind:key="t.key">{{t.name}}</option>
          </select>
        </div>
      </div>

      <br />
      <button class="btn btn-primary p-2" v-on:click.prevent="register">Submit</button>
    </form>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";

export default {
  name: "registerForm",
  data: function() {
    return {
      firstName: undefined,
      lastName: undefined,
      jmbg: undefined,
      telephone: undefined,
      country: undefined,
      city: undefined,
      address: undefined,
      email: undefined,
      password: undefined,

      confirmPassword: undefined,
      matching: "",
      type: undefined,
      types: undefined,
      typeEx: undefined,

      firstNameMessage: "",
      lastNameMessage: "",
      jmbgMessage: "",
      telephoneMessage: "",
      countryMessage: "",
      cityMessage: "",
      addressMessage: "",
      emailMessage: "",
      passwordMessage: "",
      typeMessage: "",
      typeOfExMessage: "",
      
      fnErr: true,
      lnErr: true,
      jmbgErr: true,
      telErr: true,
      cntryErr: true,
      ctyErr: true,
      addrErr: true,
      emErr: true,
      pwErr: true,
      typeErr: true,
      pwMErr: true,
      typeExErr: true
    };
  },
  props: {
    doc: Boolean,
    user: Object
  },
  watch: {
    confirmPassword() {
      if (this.confirmPassword != this.password) {
        this.matching = "Passwords are not matching!";
        this.pwMErr = true;
      } else {
        this.matching = "";
        this.pwMErr = false;
      }
    },
    firstName() {
      let firstNameInput = document.getElementsByName("firstName")[0];

      if (!this.firstName) {
        this.firstNameMessage = "Please enter your first name";
        firstNameInput.style.border = '2px solid red';
        this.fnErr = true;
      } else {
        this.firstNameMessage = "";
        firstNameInput.style.border = null;
        this.fnErr = false;
      }
    },
    lastName() {
      let lastNameInput = document.getElementsByName("lastName")[0];

      if (!this.lastName) {
        this.lastNameMessage = "Please enter your last name";
        lastNameInput.style.border = '2px solid red';
        this.lnErr = true;
      } else {
        this.lastNameMessage = "";
        lastNameInput.style.border = null;
        this.lnErr = false;
      }
    },
    jmbg() {
      let jmbgInput = document.getElementsByName("jmbg")[0];

      if (!this.jmbg) {
        this.jmbgMessage = "Please enter your JMBG";
        jmbgInput.style.border = '2px solid red';
        this.jmbgErr = true;
      } else if (isNaN(this.jmbg)) {
        this.jmbgMessage = "JMBG must be a number";
        jmbgInput.style.border = '2px solid red';
        this.jmbgErr = true;
      } else if (this.jmbg.length != 13) {
        this.jmbgMessage = "JMBG must be 13 characters long";
        jmbgInput.style.border = '2px solid red';
        this.jmbgErr = true;
      } else {
        this.jmbgMessage = "";
        jmbgInput.style.border = null;
        this.jmbgErr = false;
      }
    },
    telephone() {
      let telephoneInput = document.getElementsByName("telephone")[0];

      if (!this.telephone) {
        this.telephoneMessage = "Please enter your telephone number";
        telephoneInput.style.border = '2px solid red';
        this.telErr = true;
      } else if (isNaN(this.telephone)) {
        this.telephoneMessage = "Not a number";
        telephoneInput.style.border = '2px solid red';
        this.telErr = true;
      } else {
        this.telephoneMessage = "";
        telephoneInput.style.border = null;
        this.telErr = false;
      }
    },
    country() {
      let countryInput = document.getElementsByName("country")[0];

      if (!this.country) {
        this.countryMessage = "Please enter your country";
        countryInput.style.border = '2px solid red';
        this.cntryErr = true;
      } else {
        this.countryMessage = "";
        countryInput.style.border = null;
        this.cntryErr = false;
      }
    },
    city() {
      let cityInput = document.getElementsByName("city")[0];

      if (!this.city) {
        this.cityMessage = "Please enter your city";
        cityInput.style.border = '2px solid red';
        this.ctyErr = true;
      } else {
        this.cityMessage = "";
        cityInput.style.border = null;
        this.ctyErr = false;
      }
    },
    address() {
      let addressInput = document.getElementsByName("address")[0];

      if (!this.address) {
        this.addressMessage = "Please enter your address";
        addressInput.style.border = '2px solid red';
        this.addrErr = true;
      } else {
        this.addressMessage = "";
        addressInput.style.border = null;
        this.addrErr = false;
      }
    },
    email() {
      let emailInput = document.getElementsByName("email")[0];

      if (!this.email) {
        this.emailMessage = "Please enter your e-mail";
        emailInput.style.border = '2px solid red';
        this.emErr = true;
      } else if (!this.email.includes("@")) {
        this.emailMessage = "Invalid e-mail format";
        emailInput.style.border = '2px solid red';
        this.emErr = true;
      } else {
        this.emailMessage = "";
        emailInput.style.border = null;
        this.emErr = false;
      }
    },
    password() {
      let passwordInput = document.getElementsByName("password")[0];

      if (!this.password) {
        this.passwordMessage = "Enter a password";
        passwordInput.style.border = '2px solid red';
        this.pwMErr = true;
      } else if (this.password.length > 0 && this.password.length < 6) {
        this.passwordMessage = "Password must have at least 6 characters";
        passwordInput.style.border = '2px solid red';
        this.pwMErr = true;
      } else {
        this.passwordMessage = "";
        passwordInput.style.border = null;
        this.pwMErr = false;
      }
    },
    type() {
      let typeInput = document.getElementsByName("type")[0];

      if (!this.type) {
        this.typeMessage = "Select a type of medical staff";
        typeInput.style.border = '2px solid red';
        this.typeErr = true;
      } else {
        this.typeMessage = "";
        typeInput.style.border = null;
        this.typeErr = false;
      }
    },
    typeEx() {
      let typeExInput = document.getElementsByName("typeOfExamination")[0];

      if (!this.typeEx) {
        this.typeOfExMessage = "Select a type";
        typeExInput.style.border = '2px solid red';
        this.typeExErr = true;
      } else {
        this.typeOfExMessage = "";
        typeExInput.style.border = null;
        this.typeExErr = false;
      }
    }
  },
  mounted() {
    if (this.doc) {
      httpClient
        .get("/types/all")
        .then(response => {
          this.types = response.data;
        })
        .catch(error => {
          error;
        });
    }
  },
  methods: {
    register: function() {
      if (!this.fnErr && !this.lnErr && !this.jmbgErr && !this.telErr && !this.cntryErr &&
          !this.ctyErr && !this.addrErr && !this.emErr && !this.pwErr && !this.typeErr && !this.pwMErr && 
          !((this.type == 'Doctor') && this.typeExErr)) {
        var user = {
          firstName: this.firstName,
          lastName: this.lastName,
          jmbg: this.jmbg,
          password: this.password,
          email: this.email,
          address: this.address,
          city: this.city,
          country: this.country,
          telephone: this.telephone
        };

        this.$emit("register", user, this.type, this.typeOfExamination);
      } else {
        alert("Please fill your informations properly");
      }
    },
    firstNameBlank: function() {
      let firstNameInput = document.getElementsByName("firstName")[0];

      if (!this.firstName) {
        this.firstNameMessage = "Please enter your first name";
        firstNameInput.style.border = '2px solid red';
        this.fnErr = true;
      } else {
        this.firstNameMessage = "";
        firstNameInput.style.border = null;
        this.fnErr = false;
      }
    },
    lastNameBlank: function() {
      let lastNameInput = document.getElementsByName("lastName")[0];

      if (!this.lastName) {
        this.lastNameMessage = "Please enter your last name";
        lastNameInput.style.border = '2px solid red';
        this.lnErr = true;
      } else {
        this.lastNameMessage = "";
        lastNameInput.style.border = null;
        this.lnErr = false;
      }
    },
    jmbgBlank: function() {
      let jmbgInput = document.getElementsByName("jmbg")[0];

      if (!this.jmbg) {
        this.jmbgMessage = "Please enter your JMBG";
        jmbgInput.style.border = '2px solid red';
        this.jmbgErr = true;
      } else if (isNaN(this.jmbg)) {
        this.jmbgMessage = "JMBG must be a number";
        jmbgInput.style.border = '2px solid red';
        this.jmbgErr = true;
      } else if (this.jmbg.length != 13) {
        this.jmbgMessage = "JMBG must be 13 characters long";
        jmbgInput.style.border = '2px solid red';
        this.jmbgErr = true;
      } else {
        this.jmbgMessage = "";
        jmbgInput.style.border = null;
        this.jmbgErr = false;
      }
    },
    telephoneBlank: function() {
      let telephoneInput = document.getElementsByName("telephone")[0];
      
      if (!this.telephone) {
        this.telephoneMessage = "Please enter your telephone number";
        telephoneInput.style.border = '2px solid red';
        this.telErr = true;
      } else if (isNaN(this.telephone)) {
        this.telephoneMessage = "Not a number";
        telephoneInput.style.border = '2px solid red';
        this.telErr = true;
      } else {
        this.telephoneMessage = "";
        telephoneInput.style.border = null;
        this.telErr = false;
      }
    },
    countryBlank: function() {
      let countryInput = document.getElementsByName("country")[0];

      if (!this.country) {
        this.countryMessage = "Please enter your country";
        countryInput.style.border = '2px solid red';
        this.cntryErr = true;
      } else {
        this.countryMessage = "";
        countryInput.style.border = null;
        this.cntryErr = false;
      }
    },
    cityBlank: function() {
      let cityInput = document.getElementsByName("city")[0];

      if (!this.city) {
        this.cityMessage = "Please enter your city";
        cityInput.style.border = '2px solid red';
        this.ctyErr = true;
      } else {
        this.cityMessage = "";
        cityInput.style.border = null;
        this.ctyErr = false;
      }
    },
    addressBlank: function() {
      let addressInput = document.getElementsByName("address")[0];
      
      if (!this.address) {
        this.addressMessage = "Please enter your address";
        addressInput.style.border = '2px solid red';
        this.addrErr = true;
      } else {
        this.addressMessage = "";
        addressInput.style.border = null;
        this.addrErr = false;
      }
    },
    emailBlank: function() {
      let emailInput = document.getElementsByName("email")[0];

      if (!this.email) {
        this.emailMessage = "Please enter your e-mail";
        emailInput.style.border = '2px solid red';
        this.emErr = true;
      } else if (!this.email.includes("@")) {
        this.emailMessage = "Invalid e-mail format";
        emailInput.style.border = '2px solid red';
        this.emErr = true;
      } else {
        this.emailMessage = "";
        emailInput.style.border = null;
        this.emErr = false;
      }
    },
    passwordBlank: function() {
      let passwordInput = document.getElementsByName("password")[0];

      if (!this.password) {
        this.passwordMessage = "Enter a password";
        passwordInput.style.border = '2px solid red';
        this.pwErr = true;
      } else {
        this.addressMessage = "";
        passwordInput.style.border = null;
        this.pwErr = false;
      }
    },
    typeBlank : function() {
      let typeInput = document.getElementsByName("type")[0];

      if(!this.type) {
        this.typeMessage = "Select a type of medical staff";
        typeInput.style.border = '2px solid red';
        this.typeErr = true;
      } else {
        this.typeMessage = "";
        typeInput.style.border = null;
        this.typeErr = false;
      }
    },
    typeExBlank : function() {
      let typeExInput = document.getElementsByName("typeOfExamination")[0];

      if(!this.typeEx) {
        this.typeOfExMessage = "Select a type";
        typeExInput.style.border = '2px solid red';
        this.typeExErr = true;
      } else {
        this.typeOfExMessage = "";
        typeExInput.style.border = null;
        this.typeExErr = false;
      }
    }
  }
};
</script>

<style scoped>
#matching, #err {
  color: red;
}
</style>
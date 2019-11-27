<template>
  <div class="d-flex p-2 justify-content-center">
    <div class="d-flex flex-column p-2 justify-content-center">
      <h1>Manage doctors</h1>

      <div v-if="mode == 'VIEW'" class="d-flex flex-row flex-wrap p-2 justify-content-center">
        <div
          class="card border-primary mb-3"
          style="max-width: 20rem; max-height: 18rem; float: left; margin: 10px"
        >
          <div class="card-body">
            <h4 class="card-title">First name: {{firstName}} {{lastName}}</h4>
            <p class="card-text">Address: {{address}}</p>
            <p class="card-text">Jmbg: {{jmbg}}</p>
            <p class="card-text">Telephone: {{telephone}}</p>
            <p class="card-text">Email: {{email}}</p>
            <p class="card-text">Clinic: {{clinic}}</p>
            <button type="button" class="btn btn-primary" v-on:click="edit">Edit</button>
            <button type="button" class="btn btn-danger" v-on:click="remove">Delete</button>
          </div>
        </div>
      </div>

      <div v-else class="d-flex flex-column p-2 justify-content-center">
        <form class="d-flex flex-column justify-content-center">
          <legend>Change doctor</legend>
          <label class="p-2">Name</label>
          <input type="text" class="p-2" id="firstName" name="firstName" v-model="firstName" />

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
          <button type="submit" class="btn btn-primary" v-on:click="save">Save</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "listDiagnosis",
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
      clinic: undefined,
      mode: "VIEW"
    };
  },
  methods: {
    edit: function() {
      this.mode = "EDIT";
    },
    save: function() {
      this.mode = "VIEW";
    },
    remove: function() {}
  }
};
</script>
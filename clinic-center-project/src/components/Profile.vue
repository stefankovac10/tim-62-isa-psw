<template>
  <div class="d-flex p-2 justify-content-center">
    <div class="d-flex flex-column p-2">
      <h1>Clinic administrator</h1>
      <button type="button" class="btn btn-primary" v-on:click="editProfile">Edit profile</button>

      <p>Name: {{ name }} {{ surname }}</p>
      <p>JMBG: {{ jmbg }}</p>
      <p>Telephone: {{ telephone }}</p>
      <p>Address: {{ address }}, {{ city }}, {{ country }}</p>
      <p>Email: {{ email }}</p>
      <p>Assigned clinic: {{ clinic }}</p>
    </div>
  </div>
</template>

<script>
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
      clinic: undefined
    };
  },
  methods: {
    editProfile: function() {
      this.$router.push("/cadmin/editProfile");
    }
  }
};
</script>

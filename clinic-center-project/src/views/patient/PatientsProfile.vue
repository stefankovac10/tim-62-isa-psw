<template>
  <div class="d-flex p-2 justify-content-center">
    <div class="d-flex flex-column p-2">
      <h1>Patient's info:</h1>
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
      name: "Petar",
      surname: "Perić",
      jmbg: "0505997500025",
      telephone: "011789465",
      country: "Srbija",
      city: "Beograd",
      address: "Knez Mihajlova",
      email: "pperic@gmail.com",
      clinic: "Klinika1"
    };
  }
};
</script>

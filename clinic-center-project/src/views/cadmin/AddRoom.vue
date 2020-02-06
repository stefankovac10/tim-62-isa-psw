<template>
  <div class="d-flex p-2 justify-content-center">
    <form id="login" accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
      <h1>Add room</h1>
      <br />

      <label class="p-2">Room number</label>
      <input type="text" class="p-2" id="number" name="number" v-model="number" />

      <label class="p-2">Room name</label>
      <input type="text" class="p-2" id="name" name="name" v-model="name" />

      <label class="p-2">Room type</label>
      <select class="p-2" id="type" name="type" v-model="type">
        <option>Examination</option>
        <option>Operation</option>
      </select>
      <br />
      <button class="btn btn-primary p-2" v-on:click.prevent="addRoom">Submit</button>
    </form>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";

export default {
  data: function() {
    return {
      name: undefined,
      number: undefined,
      type: undefined,
      clinic: undefined
    };
  },
  mounted() {
    httpClient
      .get("/users/admin/mail/" + localStorage.getItem("Email"))
      .then(response => {
        this.clinic = response.data.clinic;
      })
      .catch(() => {
        this.$vToastify.error({
          body: "Error getting clinic",
          title: "Error",
          type: "error",
          canTimeout: true,
          append: false,
          duration: 2000
        });
      });
  },
  methods: {
    addRoom: function() {
      var room = {
        name: this.name,
        number: this.number,
        clinic: this.clinic
      };

      let path = "/rooms/" + this.type.toLowerCase();
      httpClient
        .post(path, room)
        .then(() => {
          this.$vToastify.success({
            body: "Room " + this.name + " has been added.",
            title: "Success",
            type: "success",
            canTimeout: true,
            append: false,
            duration: 2000
          });
        })
        .catch(() => {
          this.$vToastify.error({
            body: "Error while adding room",
            title: "Error",
            type: "error",
            canTimeout: true,
            append: false,
            duration: 2000
          });
        })
        .then(() => {
          this.$router.push("/cadmin/rooms");
        });
    }
  }
};
</script>

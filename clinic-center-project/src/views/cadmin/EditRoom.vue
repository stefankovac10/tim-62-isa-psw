<template>
  <div class="d-flex p-2 justify-content-center">
    <form id="login" accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
      <h1>Add room</h1>
      <br />

      <label class="p-2">Room number</label>
      <input type="text" class="p-2" id="number" name="number" v-model="number" />

      <label class="p-2">Room name</label>
      <input type="text" class="p-2" id="name" name="name" v-model="name" />

      <!-- <label class="p-2">Room type</label>
      <select class="p-2" id="type" name="type" v-model="type">
        <option>Examination</option>
        <option>Operation</option>
      </select>-->
      <br />
      <button class="btn btn-primary p-2" v-on:click.prevent="update">Submit</button>
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
      room: undefined
    };
  },
  mounted() {
    httpClient
      .get(
        "/rooms/" + this.$route.params.roomType + "/" + this.$route.params.id
      )
      .then(response => {
        this.room = response.data;
        this.name = this.room.name;
        this.number = this.room.number;
      })
      .catch(error => {
        alert(error);
      });
  },
  methods: {
    update: function() {
      let room = {
        id: this.room.id,
        name: this.name,
        number: this.number
      };

      httpClient
        .put("/rooms/" + this.room.type, room)
        .then(response => {
          response;
          this.$router.push("/cadmin/rooms");
        })
        .catch(error => {
          alert(error);
        });
        this.$vToastify.info({
          body: "Room has been edited." ,
          title: "Success",
          type: "success",
          canTimeout: true,
          append: false, duration: 2000
        });
    }
  }
};
</script>
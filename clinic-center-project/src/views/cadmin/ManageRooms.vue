<template>
  <div class="d-flex p-2">
    <div class="d-flex flex-column justify-content-center">
      <div id="slct" class="d-flex flex-column col-sm-4 justify-content-center">
        <h1>Manage rooms</h1>
        <label class="p-2">Room type</label>
        <select class="p-2" id="type" name="type" v-model="type">
          <option>Examination</option>
          <option>Operation</option>
        </select>
      </div>
      <div class="d-flex flex-row flex-wrap">
        <div
          class="card border-primary"
          v-for="room in rooms"
          v-bind:key="room.id"
          style="max-width: 20rem; max-height: 18rem; float: left; margin: 10px"
        >
          <div class="card-body">
            <h4 class="card-title">Room name: {{room.name}}</h4>
            <p class="card-text">Number: {{room.number}}</p>
            <!-- <p class="card-text">Clinic: {{room.clinic}}</p> -->
            <button
              type="button"
              class="btn btn-primary"
              data-toggle="modal"
              data-target="#editModal"
              data-whatever="@mdo"
              v-on:click="edit(room)"
            >Edit</button>
            <button type="button" class="btn btn-danger" v-on:click="remove(room)">Delete</button>
          </div>
        </div>
      </div>
    </div>
    <div id="editModal" class="modal">
      <div class="modal-dialog justify-content-center" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Edit room</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <form id="login" accept-charset="UTF-8" class="d-flex flex-column">
            <div class="modal-body d-flex flex-column">
              <label class="p-2">Room number</label>
              <input type="text" class="p-2" id="number" name="number" v-model="number" />

              <label class="p-2">Room name</label>
              <input type="text" class="p-2" id="name" name="name" v-model="name" />
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
      rooms: undefined,
      type: undefined,
      name: undefined,
      number: undefined
    };
  },
  watch: {
    type: function() {
      let path = "/rooms/" + this.type.toLowerCase() + "/all";
      httpClient
        .get(path)
        .then(response => {
          this.rooms = response.data;
        })
        .catch(error => {
          if (error.response.status == 302) {
            this.rooms = error.response.data;
          }
        });
    }
  },
  methods: {
    edit: function(room) {
      this.room = room;
      this.number = room.number;
      this.name = room.name;
    },
    remove: function(room) {
      httpClient
        .delete("/rooms/" + this.type.toLowerCase() + "/" + room.id)
        .then(() => {
          this.rooms.splice(this.rooms.indexOf(room), 1);
        })
        .catch(error => {
          alert(error);
        });
    },
    update: function() {
      // let room = {
      //   id: this.room.id,
      //   name: this.name,
      //   number: this.number
      // };

      httpClient
        .put("/rooms/" + this.room.type, {
          id: this.room.id,
          name: this.name,
          number: this.number
        })
        .then(response => {
          response;
          this.$router.push("/cadmin/rooms");
        })
        .catch(error => {
          alert(error);
        });
    }
  }
};
</script>
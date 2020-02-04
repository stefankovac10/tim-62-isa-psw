<template>
  <div>
    <div id="rooms" class="justify-content-center">
      <table class="table table-hover">
        <thead>
          <tr>
            <th scope="col">Name</th>
            <th scope="col">Number</th>
            <th scope="col">First available</th>
            <th scope="col">Reserve</th>
          </tr>
        </thead>
        <tbody>
          <tr class="table-primary" v-for="room in rooms" v-bind:key="room.id">
            <td>{{room.name}}</td>
            <td>{{room.number}}</td>
            <td>{{room.next}}</td>
            <td>
              <button type="button" class="btn btn-success" v-on:click="reserve(room)">Reserve</button>
            </td>
          </tr>
        </tbody>
      </table>
      <div>
        <ul class="pagination">
          <li class="page-item active" v-for="i in pages" v-bind:key="i">
            <a class="page-link" v-on:click="nextPage(i - 1)">{{i}}</a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
import moment from "moment";

export default {
  data: function() {
    return {
      pages: undefined,
      rooms: undefined
    };
  },
  props: {
    type: String,
    id: Number
  },
  mounted() {
    httpClient
      .get("/rooms/examinationRequest/" + this.id + "/0")
      .then(response => {
        this.rooms = response.data;
        for (const room of this.rooms) {
          room.next = moment(room.nextAvailable).format(
            "dddd, MMMM Do YYYY, h:mm:ss"
          );
        }
        this.pages = response.data[0].pages;
      })
      .catch(error => {
        alert(error);
      });
  },
  methods: {
    reserve: function(room) {
      let del = -1;
      let roomWithRequest = room;
      roomWithRequest.requestId = this.id;
      roomWithRequest.nextAvailable = moment(
        roomWithRequest.nextAvailable
      ).toISOString();
      httpClient
        .put("/cadmin/reserve/", roomWithRequest)
        .then(() => {
          this.$vToastify.info({
            body: "Successfully reserved room for examination",
            title: "Success",
            type: "success",
            canTimeout: true,
            append: false,
            duration: 2000
          });
        })
        .catch(() => {
          this.$vToastify.error({
            body: "Error while reserving examination",
            title: "Error",
            type: "error",
            canTimeout: true,
            append: false,
            duration: 2000
          });
        });
      for (let i = 0; i < this.rooms.length; i++) {
        if (this.rooms[i] === room) {
          del = i;
          break;
        }
      }
      this.rooms.splice(del, del);
      this.$emit("reserved");
    }
  }
};
</script>
<template>
  <div class="d-flex p-2">
    <div class="d-flex flex-column justify-content-center">
      <div id="slct" class="d-flex flex-row justify-content-center">
        <div class="d-flex flex-column m-2">
          <h2 class="m-2" for="search">Search rooms</h2>
          <div id="search" class="d-flex flex-row m-2">
            <label class="m-1" for="nejm">Name</label>
            <input class="m-1" type="text" name="nejm" id="searchName" v-model="searchName" />
            <label class="m-1" for="num">Number</label>
            <input class="m-1" type="text" name="num" id="searchNumber" v-model="searchNumber" />
            <label class="m-1" for="dejt">Date</label>
            <input class="m-1" type="date" name="dejt" id="searchDate" v-model="searchDate" />
            <button v-on:click="searchRooms">Search</button>
          </div>
        </div>
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
            <p
              class="card-text"
              v-if="room.nextAvailable != undefined"
            >Next available: {{room.dateNext}}</p>
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
      <div class="d-flex justify-content-center">
        <ul class="pagination">
          <!-- <li class="page-item disabled">
              <a class="page-link" href="#">&laquo;</a>
          </li>-->
          <li class="page-item active" v-for="i in pages" v-bind:key="i">
            <a class="page-link" v-on:click="nextPage(i - 1)">{{i}}</a>
          </li>
          <!-- <li class="page-item">
              <a class="page-link">&raquo;</a>
          </li>-->
        </ul>
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
import EventBus from "@/services/EventBus.js";
import moment from "moment";

export default {
  data: function() {
    return {
      rooms: [],
      examination: [],
      operation: [],
      type: undefined,
      name: undefined,
      number: undefined,
      searchName: undefined,
      searchNumber: undefined,
      searchDate: undefined,
      request: undefined,
      page: 0,
      pages: 1
    };
  },
  created() {
    EventBus.$on("search", id => {
      this.request = id;
    });
  },
  mounted() {
    httpClient
      .get("/rooms/all/0")
      .then(response => {
        this.rooms = response.data;
      })
      .catch(error => {
        alert(error);
      });
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
    },
    searchRooms: function() {
      let sName = this.searchName;
      let sNum = this.searchNumber;
      let sDate = this.searchDate;
      if (this.searchName === undefined || this.searchName === "") sName = "_";
      if (
        this.searchNumber === undefined ||
        this.searchNumber < 0 ||
        this.searchNumber === ""
      )
        sNum = -1;
      if (this.searchDate === undefined || this.searchDate === "") {
        sDate = new moment().toISOString();
      }
      httpClient
        .get(
          "/rooms/search/" +
            sName +
            "/" +
            sNum +
            "/" +
            sDate +
            "/" +
            -1 +
            "/" +
            this.page
        )
        .then(response => {
          this.rooms = response.data;
          this.pages = response.data[0].pages;
          for (const room of this.rooms) {
            room.dateNext = moment(room.nextAvailable).toString();
          }
        })
        .catch(error => {
          alert(error);
        });
    }
  }
};
</script>
<template>
  <div class="d-flex p-2">
    <div class="d-flex flex-column justify-content-center">
      <h1>Manage rooms</h1>
      <div id="slct" class="d-flex flex-row justify-content-center">
        <div class="d-flex flex-column m-2">
          <label class="m-2" for="search">Search patients</label>
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
      page: 0,
      pages: 7
    };
  },
  mounted() {
    httpClient
      .get("/rooms/examination/all")
      .then(response => {
        this.examination = response.data;
        for (let i = 0; i < this.examination.length; i++) {
          this.rooms.push(this.examination[i]);
        }
      })
      .catch(error => {
        if (error.response.status == 302) {
          this.examination = error.response.data;
          for (let i = 0; i < this.examination.length; i++) {
            this.rooms.push(this.examination[i]);
          }
        }
      });

    httpClient
      .get("/rooms/operation/all")
      .then(response => {
        this.operation = response.data;
        for (let i = 0; i < this.operation.length; i++) {
          this.rooms.push(this.operation[i]);
        }
      })
      .catch(error => {
        if (error.response.status == 302) {
          this.operation = error.response.data;
          for (let i = 0; i < this.operation.length; i++) {
            this.rooms.push(this.operation[i]);
          }
        }
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
    },
    searchRooms: function() {
      httpClient
        .get(
          "/rooms/search/" +
            this.searchName +
            "/" +
            this.searchNumber +
            "/" +
            this.searchDate +
            "/" +
            this.page
        )
        .then(response => {
          this.rooms = response.data;
        })
        .catch(error => {
          alert(error);
        });
    }
  }
};
</script>
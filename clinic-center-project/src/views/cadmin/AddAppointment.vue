<template>
  <div class="d-flex p-2 justify-content-center">
    <form id="app" accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
      <h1 class="p-2">Add appointment</h1>
      <br />

      <label class="p-2">Date and time</label>
      <input type="datetime-local" class="p-2" id="start" name="start" v-model="start" />

      <div class="d-flex flex-column">
        <label for="tyepOfExamination">Type of examination</label>
        <select name="typeOfExamination" id="typeEx" v-model="typeOfExamination">
          <option v-for="t in types" v-bind:value="t" v-bind:key="t.key">{{t.name}}</option>
        </select>
      </div>

      <div class="d-flex flex-column">
        <label for="duration">Duration</label>
        <select name="duration" id="duration" v-model="duration">
          <option v-for="d in ds" v-bind:value="d" v-bind:key="d.key">{{d}}</option>
        </select>
      </div>

      <div class="d-flex flex-column">
        <label for="examRoom">Examination room</label>
        <select name="examRoom" id="examRoom" v-model="examinationRoom">
          <option v-for="exam in examRooms" v-bind:value="exam" v-bind:key="exam.key">{{exam.name}}</option>
        </select>
      </div>

      <div class="d-flex flex-column">
        <label for="doctor">Doctor</label>
        <select name="doctor" id="doctor" v-model="doctor">
          <option
            v-for="doc in doctors"
            v-bind:value="doc"
            v-bind:key="doc.key"
          >{{doc.firstName}} {{doc.lastName}}</option>
        </select>
      </div>

      <label for="price">Price</label>
      <input type="number" name="price" id="price" v-model="price" />

      <br />
      <button class="btn btn-primary p-2" v-on:click.prevent="addAppointment">Submit</button>
    </form>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";

export default {
  data: function() {
    return {
      start: undefined,
      types: undefined,
      typeOfExamination: undefined,
      ds: [5, 10, 15, 20, 25, 30],
      duration: undefined,
      examRooms: undefined,
      examinationRoom: undefined,
      doctors: undefined,
      doc: undefined,
      price: undefined
    };
  },
  mounted() {
    httpClient
      .get("/types/all")
      .then(response => {
        this.types = response.data;
      })
      .catch(() => {
        this.$vToastify.error({
          body: "Could not retrieve types of examination",
          title: "Error",
          type: "error",
          canTimeout: true,
          append: false,
          errorDuration: 2000
        });
      });
    httpClient
      .get("/rooms/examination/all")
      .then(response => {
        this.examRooms = response.data;
      })
      .catch(error => {
        if (error.response.status == 302) {
          this.examRooms = error.response.data;
        } else {
          this.$vToastify.error({
            body: "Could not retrieve available rooms",
            title: "Error",
            type: "error",
            canTimeout: true,
            append: false,
            errorDuration: 2000
          });
        }
      });
    httpClient
      .get("/users/doc/all") // by clinic
      .then(response => {
        this.doctors = response.data;
      })
      .catch(() => {
        this.$vToastify.error({
          body: "Could not retrieve available doctors",
          title: "Error",
          type: "error",
          canTimeout: true,
          append: false,
          errorDuration: 2000
        });
      });
  },
  methods: {
    addAppointment: function() {
      let appointment = {
        date: this.start,
        type: this.typeOfExamination,
        price: this.price,
        examinationRoom: this.examinationRoom,
        doctor: this.doctor
        //clinic: this.clinic
      };
      alert(this.typeOfExamination);
      alert(this.examinationRoom);
      alert(this.doctor);

      httpClient
        .post("/examination/addQuick", appointment)
        .then(() => {
          this.$vToastify.success({
            body: "New appointment created",
            title: "Success",
            type: "success",
            canTimeout: true,
            append: false,
            successDuration: 2000
          });
        })
        .catch(() => {
          this.$vToastify.error({
            body: "Could not create new appointment",
            title: "Error",
            type: "error",
            canTimeout: true,
            append: false,
            errorDuration: 2000
          });
        });
      this.$router.push("/cadmin/clinic");
    }
  }
};
</script>

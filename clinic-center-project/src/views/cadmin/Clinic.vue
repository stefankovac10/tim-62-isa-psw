<template>
  <div class="d-flex p-2 justify-content-center">
    <div v-if="!loading" class="d-flex flex-column p-2">
      <h1>Clinic</h1>
      <button
        id="btn"
        data-toggle="modal"
        data-target="#editModal"
        data-whatever="@mdo"
        v-on:click.prevent="edit()"
      >Edit</button>
      <label>Name: {{clinic.name}}</label>
      <label>Description: {{clinic.description}}</label>
      <label>Address: {{clinic.address}}</label>

      <yandex-map
        id="map"
        :settings="settings"
        :coords="coords"
        @map-was-initialized="mapInitialized"
      >
        <!--Markers-->
        <ymap-marker :coords="coords" marker-id="123" hint-content="some hint"></ymap-marker>
      </yandex-map>

      <label>Available examinations: TBA: LIST</label>
      <label>Staff:</label>
      <div class="d-flex flex-row flex-wrap">
        <div
          v-for="ms in clinic.medicalStaff"
          v-bind:key="ms.id"
          class="card border-primary mb-3"
          style="max-width: 20rem; max-height: 18rem; float: left; margin: 10px"
        >
          <div class="card-body">
            <!-- <h4 class="card-title">{{req.medicalStaff.firstName}} {{req.medicalStaff.lastName}}</h4> -->
            <h4 class="card-title">{{ms.fistName}} {{ms.lastName}}</h4>
            <p class="card-text">Address: {{ms.address}}, {{ms.city}}, {{ms.country}}</p>
            <p class="card-text">E-mail: {{ms.email}}</p>
            <p class="card=text">Telephone: {{ms.telephone}}</p>
          </div>
        </div>
      </div>
      <label>Rooms:</label>
      <div class="d-flex flex-row flex-wrap">
        <div
          v-for="room in clinic.rooms"
          v-bind:key="room.id"
          class="card border-primary mb-3 d-flex flex-row flex-wrap"
          style="max-width: 20rem; max-height: 18rem; float: left; margin: 10px"
        >
          <div class="card-body">
            <!-- <h4 class="card-title">{{req.medicalStaff.firstName}} {{req.medicalStaff.lastName}}</h4> -->
            <h4 class="card-title">Name: {{room.name}}</h4>
            <p class="card-text">Number: {{room.number}}</p>
          </div>
        </div>
      </div>
      <label>Pricelist: TBA: list</label>
    </div>
    <div id="editModal" class="modal">
      <div class="modal-dialog justify-content-center" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Edit clinic's info</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <form id="login" accept-charset="UTF-8" class="d-flex flex-column">
            <div class="modal-body d-flex flex-column">
              <label>Name:</label>
              <input type="text" class="p-2" id="name" name="name" v-model="name" />
              <label>Description:</label>
              <input
                type="text"
                class="p-2"
                id="description"
                name="description"
                v-model="description"
              />
              <label>Address:</label>
              <input type="text" class="p-2" id="address" name="address" v-model="address" />
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
import { yandexMap, ymapMarker } from "vue-yandex-maps";

export default {
  data: function() {
    return {
      clinic: undefined,
      loading: true,
      map: null,
      myMap: undefined,
      name: undefined,
      description: undefined,
      address: undefined,
      coords: [45.250746, 19.827443],
      settings: {
        apiKey: "e5347c74-2358-4b87-9061-9e16f138fc78",
        lang: "en_US",
        coordorder: "latlong",
        version: "2.1"
      }
    };
  },
  components: {
    yandexMap,
    ymapMarker
  },
  mounted() {
    this.loading = true;
    httpClient
      .get("/clinics/1")
      .then(response => {
        this.clinic = response.data;
        this.loading = false;
        this.address = this.clinic.address;
      })
      .catch(error => {
        if (error.response != undefined && error.response.status == 302) {
          this.response = error.response.data;
        }
      });
  },
  methods: {
    edit: function() {
      this.name = this.clinic.name;
      this.description = this.clinic.description;
      this.address = this.clinic.address;
    },
    update: function() {
      httpClient
        .put("/clinics", {
          id: this.clinic.id,
          name: this.name,
          description: this.description,
          address: this.address
        })
        .then(response => {
          response;
          this.$router.push("/cadmin/clinic"); // " + this.id);
        })
        .catch(error => {
          alert(error);
        });
    },
    mapInitialized: function() {
      // let addressWithPluses = encodeURI(this.address);

      let uri =
        "https://geocode-maps.yandex.ru/1.x/?apikey=" +
        this.settings.apiKey +
        "&format=json&geocode=Bulevar+despota+Stefana";

      httpClient
        .get(uri)
        .then(response => {
          alert(response.data);
        })
        .catch(() => {
          this.$vToastify.error({
            body: "Error retrieving address from Yandex maps",
            title: "Error",
            type: "error",
            canTimeout: true,
            append: false,
            errorDuration: 2000
          });
        });
    }
  }
};
</script>

<style scoped>
#map {
  height: 300px;
  width: 500px;
}
#btn {
  width: 80px;
}
</style>
<template>
  <div class="d-flex p-2 justify-content-center">
    <div v-if="!loading" class="d-flex flex-column p-2">
      <h1>Clinic</h1>
      <button id="btn" v-on:click.prevent="edit(clinic.id)">Edit</button>
      <label>Name: {{clinic.name}}</label>
      <label>Description: {{clinic.description}}</label>
      <label>Address: {{clinic.address}}</label>
      <yandex-map class="map" id="map" v-on:created="mapCreated"></yandex-map>

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
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
import ymaps from "vue-yandex-map";

export default {
  data: function() {
    return {
      clinic: undefined,
      loading: true,
      map: null,
      map_data: []
    };
  },
  mounted() {
    this.loading = true;
    httpClient
      .get("/clinics/1")
      .then(response => {
        this.clinic = response.data;
        this.loading = false;
      })
      .catch(error => {
        if (error.response != undefined && error.response.status == 302) {
          this.response = error.response.data;
        }
      });
  },
  methods: {
    edit: function(id) {
      this.$router.push("/cadmin/editClinic/" + id);
    },
    mapCreated: function() {
      this.map = new ymaps.Map(
        "map",
        {
          center: [55.751574, 37.573856],
          zoom: 16
        },
        {
          searchControlProvider: "yandex#search"
        }
      );

      this.mapObjectManager = new ymaps.ObjectManager({
        clusterize: false,
        gridSize: 60,
        clusterMinClusterSize: 5,
        clusterHasBalloon: true, // Опции кластеров задаются с префиксом cluster.
        geoObjectOpenBalloonOnClick: false // Опции геообъектов задаются с префиксом geoObject
      });

      this.map.behaviors.disable("drag");

      // set ObjectManager events
      this.map.events.add(["click"], function() {
        this.mapObjectManager.objects.balloon.close();
      });

      let myGeoObject = new ymaps.GeoObject(
        {
          // Описание геометрии.
          geometry: {
            type: "Point",
            coordinates: [45.247834, 19.850956]
          },
          // Свойства.
          properties: {
            // Контент метки.
            iconContent: "Я тащусь",
            hintContent: "Ну давай уже тащи"
          }
        },
        {
          // Опции.
          // Иконка метки будет растягиваться под размер ее содержимого.
          preset: "islands#blackStretchyIcon",
          // Метку можно перемещать.
          draggable: true
        }
      );
      this.map.geoObjects.add(myGeoObject);

      var searchControl = new ymaps.control.SearchControl({
        options: {
          provider: "yandex#map"
        }
      });
      searchControl.search("Дворцовая площадь, 2");
      searchControl.search(this.clinic.address);
      var result = searchControl.getResult(0);
      result.then(
        function(res) {
          alert("Результат " + res);
        },
        function(err) {
          alert("Ошибка", err);
        }
      );
      alert(result);
    }
  }
};
</script>

<style scoped>
#map {
  height: 200px;
  width: 280px;
}
#btn {
  width: 80px;
}
</style>
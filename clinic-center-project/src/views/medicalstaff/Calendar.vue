<template>
  <div class="d-flex p-2 justify-content-center">
    <FullCalendar
      :plugins="calendarPlugins"
      :config="config"
      :events="events"
      :selectable="true"
      :weekends="true"
      :header="{
        left: 'prevYear,prev,next,nextYear today',
        center: 'title',
        right: 'dayGridMonth, timeGridWeek, timeGridDay, listWeek', 
      }"
      @eventClick="handleClick"
    />
    <modals-container />
  </div>
</template>

<script>
require("@fullcalendar/core/main.min.css");
require("@fullcalendar/daygrid/main.min.css");
require("@fullcalendar/timegrid/main.min.css");

import FullCalendar from "@fullcalendar/vue";
import DayGridPlugin from "@fullcalendar/daygrid";
import TimeGridPlugin from "@fullcalendar/timegrid";
import InteractionPlugin from "@fullcalendar/interaction";
import ListPlugin from "@fullcalendar/list";
import { httpClient } from "@/services/Api.js";

import EventModal from "@/services/EventModal.vue";
export default {
  data: function() {
    return {
      title: undefined,
      operations: [],
      examinations: [],
      vacationsRequest: [],
      examination: {},
      calendarPlugins: [
        DayGridPlugin,
        TimeGridPlugin,
        InteractionPlugin,
        ListPlugin
      ],
      events: [],
      config: {
        defaultView: "month",
        editable: false
      }
    };
  },
  mounted() {
    httpClient
      .get(
        "/operation/doc/" +
          localStorage.getItem("Email") +
          "/" +
          localStorage.getItem("Authority")
      )
      .then(response => {
        this.operations = response.data;
        var i;
        for (i = 0; i < this.operations.length; i++) {
          this.events.push({
            title: "Operation",
            start: this.operations[i].date,
            allDay: false,
            id: this.operations[i].id
          });
        }
      })
      .catch(error => {
        this.error = error;
      });

    httpClient
      .get(
        "/examination/doc/" +
          localStorage.getItem("Email") +
          "/" +
          localStorage.getItem("Authority")
      )
      .then(response => {
        this.examinations = response.data;
        var i;
        for (i = 0; i < this.examinations.length; i++) {
          this.events.push({
            title: "Examination",
            start: this.examinations[i].date,
            allDay: false,
            id: this.examinations[i].id,
            color: "#378006"
          });
        }
      })
      .catch(error => {
        this.error = error;
      });

    httpClient
      .get(
        "/users/vacationRequest/" +
          localStorage.getItem("Email")
      )
      .then(response => {
        this.vacationsRequest = response.data;
        var i;
        for (i = 0; i < this.vacationsRequest.length; i++) {
          this.events.push({
            title: "Vacation",
            start: this.vacationsRequest[i].startDate,
            end: this.vacationsRequest[i].endDate,
            id: this.vacationsRequest[i].id,
            color: "#378999"
          });
        }
      })
      .catch(error => {
        this.error = error;
      });
  },
  components: {
    FullCalendar
  },
  methods: {
    handleClick: function(arg) {
      if(arg.event.title === "Vacation"){
        return;
      }
      this.$modal.show(EventModal, {
        event: arg.event,
        id: arg.event.id
      });
    }
  }
};
</script>


<template>
  <div class="d-flex p-2 justify-content-center">
      <FullCalendar :plugins="calendarPlugins" 
      :config="config" 
      :events="events" 
      :selectable="true"
      :header="{
        left: 'prevYear,prev,next,nextYear today',
        center: 'month,agendaWeek,agendaDay,list',
        right: 'title', 
      }"
      @eventRender="eventRender"/>
  </div>
</template>

<script>
import { FullCalendar } from 'vue-full-calendar'
import dayGridPlugin from '@fullcalendar/daygrid'
import interactionPlugin from '@fullcalendar/interaction';
import { httpClient } from "@/services/Api.js";
import { BPopover } from 'bootstrap-vue'  
export default {
  data: function() {
    return {
      operations: [],
      examinations: [],
      examination: {},
      calendarPlugins: [ interactionPlugin, dayGridPlugin ],
      events: [],
      config: {
                defaultView: 'month',
                editable:true,
            },
    };
  },
  mounted(){
      httpClient
      .get("/operations/doc/"+ localStorage.getItem("Email"))
      .then(response => {
        this.operations = response.data;
      })
      .catch(error => {
        this.error = error;
      });

      httpClient
      .get("/examination/doc/12")
      .then(response => {
        this.examinations = response.data;
        var i;
        for(i=0; i<this.examinations.length; i++){
          this.events.push({  
            title  : '\nExamination\n'+ this.examinations[i].patient.firstName +' ' + this.examinations[i].patient.lastName + '\n Type: '+ this.examinations[i].type.name ,
            start  : this.examinations[i].date,
            allDay : false,       
        });
      }
      })
      .catch(error => {
        this.error = error;
      });
  },
  components: {
    FullCalendar,
  },
  methods:{
    eventRender: function (args) {
    //console.log(args)
    let titleStr = 'xxxx'
    let contentStr = 'xxxx'

    new BPopover({propsData: {
      title: titleStr,
      content: contentStr,
      placement: 'auto',
      boundary: 'scrollParent',
      boundaryPadding: 5,
      delay: 500,
      offset: 0,
      triggers: 'hover',
      html: true,
      target: args.el,
    }}).$mount()
    }
  } 
};
</script>

<style lang='scss'>

@import '~@fullcalendar/core/main.css';
@import '~@fullcalendar/daygrid/main.css';

</style>
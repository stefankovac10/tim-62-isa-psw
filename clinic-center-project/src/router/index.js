import Vue from "vue";
import VueRouter from "vue-router";
import Register from "@/views/homepage/Register.vue";
import Login from "@/views/homepage/Login.vue";
import Home from "@/views/homepage/Home.vue";
import CCAHome from "@/views/ccadmin/CCAdminHome.vue";
import Clinics from "@/views/ccadmin/Clinics.vue";
import AddClinic from "@/views/ccadmin/AddClinic.vue";
import Requests from "@/views/ccadmin/Requests.vue";
import CAdminHome from "@/views/cadmin/CAdminHome.vue";
import AddAppointment from "@/views/cadmin/AddAppointment.vue";
import AddDoctor from "@/views/cadmin/AddDoctor.vue";
import AddRoom from "@/views/cadmin/AddRoom.vue";
import AddType from "@/views/cadmin/AddType.vue";
import BusinessReport from "@/views/cadmin/BusinessReport.vue";
import Clinic from "@/views/cadmin/Clinic.vue";
import ManageDoctors from "@/views/cadmin/ManageDoctors.vue";
import ManageRooms from "@/views/cadmin/ManageRooms.vue";
import ManageTypes from "@/views/cadmin/ManageTypes.vue";
import Profile from "@/views/cadmin/Profile.vue";
import AddCCAdmin from "@/views/ccadmin/AddCCAdmin";
import AddDiagnosis from "@/views/ccadmin/AddDiagnosis"
import AddMedicament from "@/views/ccadmin/AddMedicament"
import ListMedicaments from "@/views/ccadmin/ListMedicaments"
import ListDiagnosis from "@/views/ccadmin/ListDiagnosis"


Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "homepage",
    component: Home,
    children: [
      {
        path: "register",
        name: "register",
        component: Register
      },
      {
        path: "login",
        name: "login",
        component: Login
      }
    ]
  },
  {
    path: "/ccadmin",
    name: "NBCCAdmin",
    component: CCAHome,
    children: [
      {
        path: "clinics",
        name: "clinics",
        component: Clinics
      },
      {
        path: "addCCAdmin",
        name: "addCCAdmin",
        component: AddCCAdmin
      },
      {
        path: "addClinic",
        name: "addClinic",
        component: AddClinic
      },
      {
        path: "addMedicament",
        name: "addMedicament",
        component: AddMedicament
      },
      {
        path: "addDiagnosis",
        name: "AddDiagnosis",
        component: AddDiagnosis
      },
      {
        path: "ListDiagnosis",
        name: "ListDiagnosis",
        component: ListDiagnosis
      },
      {
        path: "ListMedicaments",
        name: "ListMedicaments",
        component: ListMedicaments
      },
      {
        path: "requests",
        name: "requests",
        component: Requests
      }
    ]
  },
  {
    path: "/cadmin",
    name: "CAdmin",
    component: CAdminHome,
    children: [
      {
        path: "addAppointment",
        name: "addAppointment",
        component: AddAppointment
      },
      {
        path: "addDoctor",
        name: "addDoctor",
        component: AddDoctor
      },
      {
        path: "addRoom",
        name: "addRoom",
        component: AddRoom
      },
      {
        path: "addType",
        name: "addType",
        component: AddType
      },
      {
        path: "businessReport",
        name: "businessReport",
        component: BusinessReport
      },
      {
        path: "clinic",
        name: "clinic",
        component: Clinic
      },
      {
        path: "doctors",
        name: "doctors",
        component: ManageDoctors
      },
      {
        path: "rooms",
        name: "rooms",
        component: ManageRooms
      },
      {
        path: "types",
        name: "types",
        component: ManageTypes
      },
      {
        path: "profile",
        name: "profile",
        component: Profile
      }
    ]
  }
];

const router = new VueRouter({
  routes
});

export default router;

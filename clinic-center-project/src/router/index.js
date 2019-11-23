import Vue from "vue";
import VueRouter from "vue-router";

import Register from "@/views/homepage/Register.vue";
import Login from "@/views/homepage/Login.vue";
import Home from "@/views/homepage/Home.vue";

import CCAHome from "@/views/ccadmin/CCAdminHome.vue";
import Clinics from "@/views/ccadmin/Clinics.vue";
import AddClinic from "@/views/ccadmin/AddClinic.vue";
import Requests from "@/views/ccadmin/Requests.vue";
import AddCCAdmin from "@/views/ccadmin/AddCCAdmin";
import AddCAdmin from "@/views/ccadmin/AddCAdmin";
import AddDiagnosis from "@/views/ccadmin/AddDiagnosis"
import AddMedicament from "@/views/ccadmin/AddMedicament"
import ListMedicaments from "@/views/ccadmin/ListMedicaments"
import ListDiagnosis from "@/views/ccadmin/ListDiagnosis"

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
import EditProfile from "@/views/cadmin/EditProfile.vue";

import PHomepage from "@/views/patient/PatientHome"
import PClinics from "@/views/patient/PClinics.vue"
import PExaminations from "@/views/patient/PExaminations"
import POperations from "@/views/patient/POperations"
import MedicalRecord from "@/views/patient/MedicalRecord"
import PatientsProfile from "@/views/patient/PatientsProfile"

import NHomepage from "@/views/nurse/NurseHome"
import NPatients from "@/views/nurse/NPatients"
import NVacationRequest from "@/views/nurse/NVacationRequest"
import NWorkCalendar from "@/views/nurse/NWorkCalendar"
import NProfile from "@/views/nurse/NProfile"
import Perscription from "@/views/nurse/Perscription"



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
        path: "addCAdmin",
        name: "addCAdmin",
        component: AddCAdmin
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
      },
      {
        path: "editProfile",
        name: "editProfile",
        component: EditProfile
      }
    ]
  },
  {
    path: "/patient",
    name: "Patient",
    component: PHomepage,
    children: [
      {
        path: "clinics",
        name: "clinics",
        component: PClinics
      },
      {
        path: "examinations",
        name: "examinations",
        component: PExaminations
      },
      {
        path: "operations",
        name: "operations",
        component: POperations
      },
      {
        path: "medicalrecord",
        name: "medicalrecord",
        component: MedicalRecord
      },
      {
        path: "profile",
        name: "profile",
        component: PatientsProfile
      }
    ]
  },
  {
    path: "/nurse",
    name: "Nurse",
    component: NHomepage,
    children: [
      {
        path: "patients",
        name: "patients",
        component: NPatients
      },
      {
        path: "workCalendar",
        name: "workCalendar",
        component: NWorkCalendar
      },
      {
        path: "vacationRequest",
        name: "vacationRequest",
        component: NVacationRequest
      },
      {
        path: "perscription",
        name: "perscription",
        component: Perscription
      },
      {
        path: "profile",
        name: "profile",
        component: NProfile
      }
    ]
  }
];

const router = new VueRouter({
  routes,
  mode: 'history'
});

export default router;

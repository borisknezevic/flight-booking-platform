import { createRouter, createWebHistory } from 'vue-router'

import RegistrationView from '../views/RegistrationView.vue'
import LoginView from '../views/LoginView.vue'
import FlightsView from '../views/FlightsView.vue'
import ProfileView from '../views/ProfileView.vue'
import AdminView from '../views/AdminView.vue'
import AirlineView from '../views/AirlineView.vue'
import AirportsView from '../views/AirportsView.vue'
import NewFlightView from '../views/NewFlightView.vue'
import TicketBuyingView from '../views/TicketBuyingView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: FlightsView,
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/registration',
      name: 'registration',
      component: RegistrationView
    },
    {
      path: '/flights',
      name: 'flights',
      component: FlightsView
    },
    {
      path: '/profile',
      name: 'profile',
      component: ProfileView
    },
    {
      path: '/ticketBuying',
      name: 'ticketBuying',
      component: TicketBuyingView
    },
    {
    path: '/admin',
    name: 'admin',
    redirect: '/admin/flights',
    component: AdminView,
    children: [
      {
        name: 'AdminFlights',
        path: 'flights',
        component: FlightsView,
      },
      {
        name: 'AdminAirlines',
        path: 'airlines',
        component: AirlineView,
      },
      {
        name: 'AdminAirports',
        path: 'airports',
        component: AirportsView,
      },
      {
        name: 'AdminNewFlight',
        path: 'newFlight',
        component: NewFlightView,
      },

    ]
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    }
  ]
})

export default router

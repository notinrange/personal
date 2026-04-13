import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'

import { Elements } from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";

const stripePromise = loadStripe("pk_test_51T0SfPGjcWNw6Xt2eKl4AFVmIWOPLZfhDRsO5z4yNHqZCGI2Z760CaDSM3f8zXMTjPgfhQouSvBKK6Jhvsmyfb2100PTLp5pFE");

createRoot(document.getElementById('root')!).render(

  <StrictMode>
    <Elements stripe={stripePromise}>
      <App />
    </Elements>
  </StrictMode>,
)

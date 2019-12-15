import axios from 'axios';
import moment from 'moment';

let AUTH_TOKEN = "Bearer " + localStorage.getItem("User-token");
let authTokenRequest = "Bearer " + localStorage.getItem("User-token");
let config = {
	baseURL: "http://localhost:8080/api",
	headers: {
		'Content-Type': 'application/json'
	}
};

const httpClient = axios.create(config);
// httpClient.defaults.headers.common['Authorization'] = AUTH_TOKEN;

function resetAuthTokenRequest() {
	authTokenRequest = null
}

function logout() {
	localStorage.removeItem("User-token");
	localStorage.removeItem("Expiary");
	localStorage.removeItem("Email");
	localStorage.removeItem("Authority");
}

function getAuthToken() {
	// if the current store token expires soon
	if (moment().subtract(40, 'second').isBefore(localStorage.getItem("Expires"))) {
		// if not already requesting a token
		if (authTokenRequest === null) {
			authTokenRequest = axios.post('/auth/refresh', {}, { withCredentials: true })
				.then(response => {
					// request complete and store
					resetAuthTokenRequest()
					localStorage.setItem("User-token", response.data.accessToken);
					localStorage.setItem("Email", response.data.email);
					localStorage.setItem("Authority", response.data.authority);

					let time = moment().add(response.data.expiresIn);
					localStorage.setItem("Expiary", time);
					return response.data.access_token
				})
		}
		return authTokenRequest
	}
	return localStorage.getItem("User-token");
}

axios.interceptors.request.use(function (config) {
	// Do something before request is sent
	if (!config.url.includes('login') && !config.url.includes('refresh') && !config.url.includes('reqrequest') && !config.url.includes('reset_password') && !config.url.includes('activate')) {
		config.headers['Authorization'] = 'Bearer ' + getAuthToken();
	} else {
		config.headers['Authorization'] = 'Bearer ' + AUTH_TOKEN;
	}
	return config;
}, function (error) {
	// Do something with request error
	return Promise.reject(error);
});

/** Adding the response interceptors */
httpClient.interceptors.response.use(function (config) {
	return config;
}, function (error) {
	// Prevent endless redirects (login is where you should end up)
	if (error.request !== undefined) {
		if (error.request.responseURL.includes('login')) {
			return Promise.reject(error)
		}
	}

	// If you can't refresh your token or you are sent Unauthorized on any request, logout and go to login
	if (error.request !== undefined && (error.request.responseURL.includes('refresh') || error.request.status === 401 && error.config.__isRetryRequest)) {
		logout();
		this.$router.push({ name: 'Login' })
	} else if (error.request !== undefined && error.request.status === 401) {
		error.config.__isRetryRequest = true
		return axios.request(error.config)
	}
	return Promise.reject(error)
});

export { httpClient };
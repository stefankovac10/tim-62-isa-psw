import axios from 'axios';
import moment from 'moment';

let config = {
	baseURL: "http://localhost:8080/api",
	headers: {
		'Content-Type': 'application/json'
	}
};

const httpClient = axios.create(config);
// httpClient.defaults.headers.common['Authorization'] = AUTH_TOKEN;

httpClient.interceptors.request.use(function (config) {
	// Do something before request is sent
	if (!config.url.includes('login') && !config.url.includes('refresh') && !config.url.includes('reqrequest') && !config.url.includes('reset_password') && !config.url.includes('activate')) {
		config.headers['Authorization'] = 'Bearer ' + localStorage.getItem("User-token");
	} else if (config.url.includes('refresh')) {
		config.headers['Authorization'] = 'Bearer ' + localStorage.getItem("Refresh-token");
	} else {
		config.headers['Authorization'] = 'Bearer ' + localStorage.getItem("User-token");
	}


	return config;
}, function (error) {
	// Do something with request error
	return Promise.reject(error);
});

// Add a response interceptor
httpClient.interceptors.response.use(function (response) {
	// Any status code that lie within the range of 2xx cause this function to trigger
	// Do something with response data
	return response;
}, function (error) {
	// Any status codes that falls outside the range of 2xx cause this function to trigger
	// Do something with response error
	if (moment().isAfter(localStorage.getItem("Expiary"))) {
		httpClient
			.post("/auth/refresh")
			.then(response => {
				localStorage.setItem("User-token", response.data.accessToken);

				let time = moment().add(response.data.expiresIn);
				localStorage.setItem("Expiary", time);
				// location.reload();
			})
			.catch(error => {
				alert(error);
			})
	}
	return Promise.reject(error);
});

export { httpClient };
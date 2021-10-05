# build stage
FROM node:lts-alpine as build-stage
WORKDIR /app

COPY . .
RUN npm install
RUN npm run build

# production stage
FROM nginx:stable-alpine as production-stage

RUN rm /etc/nginx/conf.d/default.conf
COPY ./myapp.conf /etc/nginx/myapp.conf
COPY --from=build-stage /app/dist /usr/share/nginx/html/app
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
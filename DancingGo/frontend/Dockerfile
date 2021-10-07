# build stage
FROM node:lts-alpine as build-stage
WORKDIR /app

COPY . .
RUN npm install
RUN npm run build

# production stage
FROM nginx:stable-alpine as production-stage

# RUN rm /etc/nginx/conf.d/default.conf
COPY ./myapp.conf /etc/nginx/conf.d/myapp.conf
COPY --from=build-stage /app/dist /usr/share/nginx/html
#RUN chmod 755 /usr/share/nginx/html/index.html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
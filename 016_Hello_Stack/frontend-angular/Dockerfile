FROM node:25 AS build
WORKDIR /src
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build

FROM nginx:1.27-alpine
# IMPORTANT: Angular 21 output is usually in dist/<app>/browser
COPY --from=build /src/dist/frontend-angular/browser /usr/share/nginx/html
EXPOSE 80

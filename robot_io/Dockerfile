# Use an official Node.js image with version 16 as the base image
FROM node:16-alpine AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the package.json and package-lock.json to the container
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application files to the container
COPY . .

# Build the React application
RUN npm run build

# Use an Nginx image to serve the built React app
FROM nginx:alpine

# Copy the build output from the Node.js container to the Nginx container
COPY --from=build /app/build /usr/share/nginx/html

# Expose port 80 for the application
EXPOSE 80

# Start Nginx server
CMD ["nginx", "-g", "daemon off;"]

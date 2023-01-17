provider "heroku" {
  api_key = var.api_key
}

variable "api_key" {
  default = ""
}
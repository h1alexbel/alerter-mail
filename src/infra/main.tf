terraform {
  backend "s3" {
    bucket = "aliaksei-bialiauski-env-tfstate"
    key    = "alerter-mail/terraform.tfstate"
    region = "us-east-1"
  }
  required_providers {
    heroku = {
      source  = "heroku/heroku"
      version = "5.2.4"
    }
  }
}
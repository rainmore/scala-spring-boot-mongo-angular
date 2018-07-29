import './polyfills';

import 'jquery/dist/jquery.min.js';

import 'bootstrap/dist/css/bootstrap.min.css';
import './scss/main.scss';

import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module';

import { enableProdMode } from '@angular/core';

enableProdMode();

platformBrowserDynamic().bootstrapModule(AppModule);

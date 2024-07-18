'use strict';
class Product {
    data = undefined;
    url = undefined;

    constructor(url) {
        this.url = url;
    }

    async sleep(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }
    
    async load() {
        let response = await fetch(this.url);
        response = await response.json();
        await this.sleep(1000);
        this.data = response.data;
    }
}
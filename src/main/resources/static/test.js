
import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    vus: 100, 
    duration: '30s', 
};

export default function () {
    const url = 'http://localhost:8080/api/bids';
    const payload = JSON.stringify({
        itemId: 1,
        userId: __VU,
        bidAmount: 30000 + __ITER 
    });

    const params = {
        headers: { 'Content-Type': 'application/json' },
    };

    const res = http.post(url, payload, params);

    check(res, {
        'status is 200': (r) => r.status === 200,
    });
    
    sleep(0.1);
}
import type { RequestConfig } from '@umijs/max';
import { request } from '@umijs/max';

function getCookieValue(name) {
  // 将document.cookie字符串按照; 分割成数组
  let cookies = document.cookie.split('; ');
  // 遍历cookie数组，寻找匹配name的cookie
  for (let cookie of cookies) {
    let [key, value] = cookie.split('=');
    if (key === name) {
      return value; // 返回找到的cookie值
    }
  }
  return null; // 如果没有找到，返回null
}

/**
 * @name 错误处理
 * pro 自带的错误处理， 可以在这里做自己的改动
 * @doc https://umijs.org/docs/max/request#配置
 */
export const requestConfig = {

  // 请求拦截器
  requestInterceptors: [
    (config) => {
    let cookies = document.cookie;
    console.log('Cookies:', cookies);
    // if (cookies) {
    //   let cookie = cookies.split(';');
    //   let token = cookie[0].split('=');
    //   console.log('Token:', token[1]);
    //   config.headers.set('Authorization', token[1]);
    // }

    const newConfig = {
      ...config,
      headers: {
        ...config.headers,
        'Authorization': getCookieValue('token'),
      },
    };

    // request.headers.set('Authorization', '123456');
      // 拦截请求配置，进行个性化处理。
      return { ...newConfig };
    },
  ],

  // 响应拦截器
  responseInterceptors: [],
};



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

            let token = getCookieValue('token');
            let newConfig;
            if (token) {
                newConfig = {
                    ...config,
                    headers: {
                        ...config.headers,
                        'Authorization': token,
                    },
                };
            } else {
                newConfig = {
                    ...config,
                    headers: {
                        ...config.headers,
                    },
                };
            }

            // 拦截请求配置，进行个性化处理。
            return {...newConfig};
        },
    ],

    // 响应拦截器
    responseInterceptors: [],
};



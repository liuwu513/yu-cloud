module.exports = {
    devServer: {
        host: '127.0.0.1',
        port: 9999,
        proxy: {
            '/oss': {
                target: 'http://127.0.0.1:8183',  // 请求本地 需要oss后台项目
                ws: true
            }
        }
    },
    // 打包时不生成.map文件 避免看到源码
    productionSourceMap: false
}
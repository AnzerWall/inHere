module.exports = {
    entry: './src/app.js',
    output: {
        path: './public/static/',
        publicPath: "./static/",
        filename: './index.js'
    },
    module: {
        loaders: [{
            test: /\.vue$/,
            loader: 'vue'
        }, {
            test: /\.scss$/,
            loaders: ["style", "css", "sass"]
        }, {
            test: /.*\.(gif|png|jpe?g)$/i,
            loaders: [
                'file?hash=sha512&digest=hex&name=[hash].[ext]',
                'image-webpack?{progressive:true, optimizationLevel: 7, interlaced: false, pngquant:{quality: "65-90", speed: 4}}'
            ]
        }, {
            test: /.*\.(svg)$/i,
            loaders: [
                // 'file?hash=sha512&digest=hex&name=[hash].[ext]'
                'image-webpack?{progressive:true, optimizationLevel: 7, interlaced: false, pngquant:{quality: "65-90", speed: 4}}'
            ]
        }, {
            test: /\.jade/,
            loader: "jade"
        }, {
            test: /\.woff(2)?(\?v=[0-9]\.[0-9]\.[0-9])?$/,
            loader: "url-loader?limit=10000&mimetype=application/font-woff"
        }, {
            test: /\.(ttf|eot|svg)(\?v=[0-9]\.[0-9]\.[0-9])?$/,
            loader: "file-loader"
        }],
    },
    watch: true,
    devtool: 'source-map'
};

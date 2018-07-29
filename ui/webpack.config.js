const webpack = require('webpack');

const devMode = process.env.NODE_ENV != 'production';

const HtmlWebpackPlugin = require('html-webpack-plugin');

// scss
const UglifyJsPlugin = require("uglifyjs-webpack-plugin");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const OptimizeCSSAssetsPlugin = require("optimize-css-assets-webpack-plugin");

module.exports = {
    entry: [
        './src/main.ts'
    ],
    output: {
        filename: devMode ? '[name].bundle.js' : '[name].[hash].bundle.js',
        chunkFilename: devMode ? '[id].chunk.js' : '[id].[hash].chunk.js'
    },
    module: {
        rules: [
            {
                test: /\.ts$/,
                use: ['awesome-typescript-loader', 'angular2-template-loader'],
                exclude: /node_modules/
            },
            {
                test: /\.(ts|js)$/,
                loaders: [
                    'angular-router-loader'
                ]
            },
            {
                test: /\.(html)$/,
                loader: 'raw-loader'
            },
            {
                test: /\.(css)$/,
                // load external css as link
                use: [{
                    loader: 'style-loader/url'
                }, {
                    loader: 'file-loader'
                }]
            },
            {
                test: /\.(scss)$/,
                use: [{
                    // loader: 'style-loader', // inject CSS to page
                    loader: devMode ? 'style-loader' : MiniCssExtractPlugin.loader
                }, {
                    loader: 'css-loader', options: { sourceMap: true }
                }, {
                    loader: 'postcss-loader', // Run post css actions
                    options: {
                        plugins: function () { // post css plugins, can be exported to postcss.config.js
                            return [
                                require('precss'),
                                require('autoprefixer')
                            ];
                        }
                    }
                }, {
                    loader: 'sass-loader', options: { sourceMap: true }
                }],
                exclude: /node_modules/
            }
        ]
    },
    resolve: {
        extensions: ['.ts', '.js', '.scss']
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: './src/index.html',
            filename: 'index.html',
            inject: 'body'
        }),
        new MiniCssExtractPlugin({
            // Options similar to the same options in webpackOptions.output
            // both options are optional
            filename: devMode ? '[name].css' : '[name].[hash].css',
            chunkFilename: devMode ? '[id].css' : '[id].[hash].css'
        }),
        new webpack.DefinePlugin({
            // global app config object
            config: JSON.stringify({
                apiUrl: 'http://localhost:8080'
            })
        }),
        new webpack.ProvidePlugin({
            $: 'jquery',
            jQuery: 'jquery'
        })
    ],
    optimization: {
        minimizer: [
            new UglifyJsPlugin({
                cache: true,
                parallel: true,
                sourceMap: true // set to true if you want JS source maps
            }),
            new OptimizeCSSAssetsPlugin({})
        ],
        splitChunks: {
            chunks: 'all'
        },
        runtimeChunk: true
    },
    watchOptions: {
        aggregateTimeout: 300,
        poll: 1000
    },
    devServer: {
        historyApiFallback: true
    }
};

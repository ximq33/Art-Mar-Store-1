module.exports = {
    // ... other configuration options ...
    module: {
        rules: [
            {
                test: /\.scss$/,
                exclude: /App\.scss$/,
                use: [
                    'style-loader',
                    'css-loader',
                    'sass-loader',
                ],
            },
        ],
    },
};